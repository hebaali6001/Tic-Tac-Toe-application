package arduino;

import java.util.Vector;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import jssc.SerialPortList;

public class demo  {
	//private String  s ;
        
           /* public class MyString{
            public String x ; 
        }*/
         Vector<String> vector= new Vector<String>();
        String s ;
    
	public void connect(String portname) {
		// now we need to serialport
		
		SerialPort port = new SerialPort(portname);
		
		try {
			port.openPort();
			port.setParams(
					
					SerialPort.BAUDRATE_9600,
					SerialPort.DATABITS_8,
					SerialPort.STOPBITS_1,
					SerialPort.PARITY_NONE
					);
			port.addEventListener(new SerialPortEventListener() {
                            @Override
                            public void serialEvent(SerialPortEvent event) {
                                if(event.isRXCHAR()) {
                                    
                                    try {
                                        s = new String();
                                        s = port.readString();
                                       vector.add(s);
                                       
                                        if ("U".equals(s)){
                                            System.out.print(s);
                                        //System.out.print("2");
                                        }
                                        if ("L".equals(s)){
                                            System.out.print(s);
                                        //System.out.print("3");
                                        }
                                        if ("R".equals(s)){
                                            System.out.print(s);
                                        //System.out.print("4");
                                        }
                                        if ("D".equals(s)){
                                           
                                        System.out.print(s);
                                        }
                                         if ("F".equals(s)){
                                           
                                        System.out.print(s);
                                        
                                        }
                                         //System.out.println(vector.lastElement());
                                    } catch (SerialPortException e) {
                                        // TODO Auto-generated catch block
                                        
                                    }
                                    
                                }
                            }
                        });
			
			
		} catch (SerialPortException e) {
                    // TODO Auto-generated catch block

		}
		
	}

	public static void main(String[] args) {
		// we need to array of string that hold all the ports
		String portlist[] = SerialPortList.getPortNames();
		
            for (String portlist1 : portlist) {
                System.out.println(portlist1);
            }
		demo obj = new demo();
		obj.connect(portlist[0]);
		
		

	}


    
    // Ignore all the other eventTypes, but you should consider the other ones.
}

//throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
  

   
