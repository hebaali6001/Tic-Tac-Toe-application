
package tic.tac.toe;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DialogPane;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.layout.AnchorPane;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;
import jssc.SerialPortList;

/**
 * FXML Controller class
 *
 * @author Team1
 */
public class Scene2FXMLController implements Initializable {

    @FXML
    private Button button0;
    @FXML
    private Button button1;
    @FXML
    private Button button2;
    @FXML
    private Button button3;
    @FXML
    private Button button4;
    @FXML
    private Button button5;
    @FXML
    private Button button6;
    @FXML
    private Button button7;
    @FXML
    private Button button8;
    @FXML
    private Menu Main_menu;
    @FXML
    private MenuItem About_B;
    @FXML
    private Label Player2_Label;
    @FXML
    private TextField oTf;
    @FXML
    private TextField xTf;
    @FXML
    private TextField Winner_Tf;
    int xpos;
    int ypos;
    Vector<String> vector;
    String s;

    int wineer_winner = 0;

    /**
     * ****************************************************************
     */
    ArrayList<ArrayList<Button>> My_buttons;
    public int player_flag = 0;
    static int x_counter = 0;
    static int o_counter = 0;

    int Audio_flag = 0;
    String path = "C:\\BackGround\\Music\\Audio.mp3";
    Media media = new Media(new File(path).toURI().toString());
    MediaPlayer mediaplayer = new MediaPlayer(media);
    @FXML
    private AnchorPane Pane_scene2;

    @FXML
    private DialogPane Mariogif;
    @FXML
    private DialogPane luigigif;

    String portlist[] = SerialPortList.getPortNames();
    SerialPort port = new SerialPort(portlist[0]);

    public Scene2FXMLController() {
        this.vector = new Vector<>();
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        My_buttons = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            My_buttons.add(new ArrayList<>());
        }
        My_buttons.get(0).add(button0);
        My_buttons.get(0).add(button3);
        My_buttons.get(0).add(button6);
        My_buttons.get(1).add(button1);
        My_buttons.get(1).add(button4);
        My_buttons.get(1).add(button7);
        My_buttons.get(2).add(button2);
        My_buttons.get(2).add(button5);
        My_buttons.get(2).add(button8);
        xpos = 0;
        ypos = 0;
    }
    /*
        Scound menu action event 
        shortcut -> Alt+Z 
     */
    @FXML
    private void Click_Sound_check(ActionEvent event) {
        if (Audio_flag == 0) {
            mediaplayer.play();
            Audio_flag = 1;
        } else {
            mediaplayer.pause();
            Audio_flag = 0;
        }
    }

    /*
        About button action event 
        1- craete an information dialog 
        2- set the project information
        shortcut -> ALt+i
     */
    @FXML
    private void About_Button(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About US");
        alert.setHeaderText("Information Alert");
        String message = "ITI ES \nTeam 1\nTic-Tac-Toe";
        alert.setContentText(message);
        alert.show();
    }

    @FXML
    private void StartNewGameAction(ActionEvent event) throws IOException {
        NewGame();

    }

    private void NewGame() throws IOException {
        x_counter = 0;
        o_counter = 0;
        xTf.setText(Integer.toString(((x_counter)) / 16));
        oTf.setText(Integer.toString(((o_counter)) / 16));
        Reset();

    }

    @FXML
    private void ResetAction(ActionEvent event) throws IOException {
        Reset();

    }

    private void Reset() throws IOException {
        wineer_winner = 0;
        My_buttons.forEach(list
                -> {
            list.forEach(button -> {
                button.setFocusTraversable(true);
                button.setText("");
                button.setDisable(false);
                button.setGraphic(null);
                Winner_Tf.setText("");
                Mariogif.setVisible(false);
                luigigif.setVisible(false);
            });
        });

    }

    /*
        Exit button action event to close scene1
        default button -> esc  
     */
    @FXML
    private void ExitAction(ActionEvent event) throws SerialPortException {
        exit();
    }

    private void exit() throws SerialPortException {
        port.closePort();
        Platform.exit();
    }

    @FXML
    private void Button_Press(ActionEvent event) throws FileNotFoundException {
        // X_image1.setVisible(true);
    }

    @FXML
    private void VsMario(ActionEvent event) throws IOException, SerialPortException {
        try {
            port.openPort();
            port.setParams(
                    SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE
            );
            port.addEventListener((SerialPortEvent event1) -> {
                if (event1.isRXCHAR()) {
                    try {
                        s = new String();
                        s = port.readString();
                        vector.add(s);
                        System.out.println(vector.lastElement());
                        if (wineer_winner == 0) {
                            move(vector.lastElement());
                        }
                    } catch (SerialPortException e) {
                        System.out.println("Error in Serial Communication");
                    } catch (IOException ex) {
                        Logger.getLogger(Scene2FXMLController.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            });

        } catch (SerialPortException e) {
            System.out.println("The Port is not Connected");

        }
    }

    public void move(String u) throws IOException, SerialPortException {
        for (Integer i = 0; i < My_buttons.size(); i++) {
            for (Integer j = 0; j < My_buttons.get(i).size(); j++) {
                My_buttons.get(i).get(j).setStyle("-fx-background-color: white;" + "-fx-font-size:40;");
            }
        }
        switch (u) {
            case "D":
                xpos++;
                if (xpos > 2) {
                    xpos = 0;
                }
                My_buttons.get(xpos).get(ypos).setStyle("-fx-background-color: yellow;" + "-fx-font-size:40;");
                break;
            case "U":
                xpos--;
                if (xpos < 0) {
                    xpos = 2;
                }
                My_buttons.get(xpos).get(ypos).setStyle("-fx-background-color: yellow;" + "-fx-font-size:40;");
                break;
            case "R":
                ypos++;
                if (ypos > 2) {
                    ypos = 0;
                }
                My_buttons.get(xpos).get(ypos).setStyle("-fx-background-color: yellow;" + "-fx-font-size:40;");
                break;
            case "L":
                ypos--;
                if (ypos < 0) {
                    ypos = 2;
                }
                My_buttons.get(xpos).get(ypos).setStyle("-fx-background-color: yellow;" + "-fx-font-size:40;");
                break;
            case "F":
                PlayerTurn(xpos, ypos);
                break;
            case "S":
                Reset();
                break;
            case "E":
                exit();
                break;
            case "N":
                NewGame();
                break;
        }
    }

    public void PlayerTurn(int xpos, int ypos) {
        My_buttons = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            My_buttons.add(new ArrayList<>());
        }
        My_buttons.get(0).add(button0);
        My_buttons.get(0).add(button3);
        My_buttons.get(0).add(button6);
        My_buttons.get(1).add(button1);
        My_buttons.get(1).add(button4);
        My_buttons.get(1).add(button7);
        My_buttons.get(2).add(button2);
        My_buttons.get(2).add(button5);
        My_buttons.get(2).add(button8);
        Button B = My_buttons.get(xpos).get(ypos);
        B.setText("X");
        My_buttons.get(xpos).remove(B);
        CheckGameOver();
        getRandomElements();
        CheckGameOver();
    }

    public void CheckGameOver() {
        My_buttons = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            My_buttons.add(new ArrayList<>());
        }
        My_buttons.get(0).add(button0);
        My_buttons.get(0).add(button3);
        My_buttons.get(0).add(button6);
        My_buttons.get(1).add(button1);
        My_buttons.get(1).add(button4);
        My_buttons.get(1).add(button7);
        My_buttons.get(2).add(button2);
        My_buttons.get(2).add(button5);
        My_buttons.get(2).add(button8);
        for (int i = 0; i < 8; i++) {
            String result1 = button0.getText() + button1.getText() + button2.getText();
            String result2 = button3.getText() + button4.getText() + button5.getText();
            String result3 = button6.getText() + button7.getText() + button8.getText();
            String result4 = button0.getText() + button4.getText() + button8.getText();
            String result5 = button2.getText() + button4.getText() + button6.getText();
            String result6 = button0.getText() + button3.getText() + button6.getText();
            String result7 = button1.getText() + button4.getText() + button7.getText();
            String result8 = button2.getText() + button5.getText() + button8.getText();

            if (result1.equals("XXX") || result2.equals("XXX") || result3.equals("XXX") || result4.equals("XXX") || result5.equals("XXX") || result6.equals("XXX") || result7.equals("XXX") || result8.equals("XXX")) {
                x_counter = x_counter + 1;
                xTf.setText(Integer.toString(((x_counter)) / 16));
                Winner_Tf.setText("Player 1 is the winner");
                wineer_winner = 1;
                My_buttons.forEach(list
                        -> {
                    list.forEach(button -> {
                        button.setFocusTraversable(true);
                        button.setFocusTraversable(false);
                        button.setDisable(true);
                    });
                });

            } else if (result1.equals("OOO") || result2.equals("OOO") || result3.equals("OOO") || result4.equals("OOO") || result5.equals("OOO") || result6.equals("OOO") || result7.equals("OOO") || result8.equals("OOO")) {
                o_counter = o_counter + 1;
                oTf.setText(Integer.toString((o_counter) / 16));
                Winner_Tf.setText("Player 2 is the winner");
                wineer_winner = 1;
                My_buttons.forEach(list
                        -> {
                    list.forEach(button -> {
                        button.setFocusTraversable(true);
                        button.setFocusTraversable(false);
                        button.setDisable(true);
                    });
                });
            }
        }
    }

    public void getRandomElements() {
        int indexi;
        int indexj;
        int count = 0;
        do {
            Random random = new Random();
            indexi = random.nextInt(My_buttons.size());
            indexj = random.nextInt(My_buttons.get(indexi).size());
            count++;
        } while (!"".equals(My_buttons.get(indexi).get(indexj).getText()) && count < 25);
        if (count < 25) {
            My_buttons.get(indexi).get(indexj).setText("O");
            My_buttons.get(indexi).remove(My_buttons.get(indexi).get(indexj));
        }
    }
}
