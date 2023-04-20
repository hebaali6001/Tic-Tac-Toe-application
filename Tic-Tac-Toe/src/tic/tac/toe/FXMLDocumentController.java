package tic.tac.toe;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

/**
 *
 * @author Team1
 *
 */
public class FXMLDocumentController implements Initializable {

    /*
        first scene components 
     */
    @FXML
    private Button bt1;                   //start Button
    @FXML
    private Button bt2;                  //Exit button
    @FXML
    private AnchorPane rootpane;        //First scene pane  
    @FXML
    private Menu Main_menu;            //Top menu bar
    @FXML
    private MenuItem About_B;        //About menu item in help menu

    /*
        Audio file in the First scene Background
     */
    int Audio_flag = 0;
    String path = "C:\\BackGround\\Music\\Audio.mp3";
    Media media = new Media(new File(path).toURI().toString());
    MediaPlayer mediaplayer = new MediaPlayer(media);

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    /*
    ****************************************************************************
    ***************** Action Events and handle-functions************************
    * **************************************************************************
     */
 /*
        Start button action event
        1- Load the FXML file of Scene to to new pane
        2- add that pane to the scene1 rootpane 
        default button -> Enter
     */
    @FXML
    private void SPlayButtonClick(ActionEvent event) throws IOException {
        AnchorPane pane = FXMLLoader.load(getClass().getResource("scene2FXML.fxml"));
        rootpane.getChildren().setAll(pane);
    }

    /*
        Exit button action event to close scene1
        default button -> esc  
     */
    @FXML
    private void Exit_Button(ActionEvent event) {
        Platform.exit();
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
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle("About US");
        alert.setHeaderText("Information Alert");
        String s = "ITI ES \nTeam 1\nTic-Tac-Toe";
        alert.setContentText(s);
        alert.show();
    }
}
