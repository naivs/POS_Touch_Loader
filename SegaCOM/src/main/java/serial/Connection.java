package serial;

import java.util.Observable;
import java.util.Observer;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;

/**
 *
 * @author ivan
 */
public class Connection extends Observable {

    /*
    Connection protocol contains three commands: 
    1. > 222 - you can assure that this tty is target device 
        expected response: > sega_ready 
    2. > 444 - start handle gamepad buttons 
        expected response: > started 
    3. > 666 - stop handle gamepad buttons 
        expected response: > stopped
     */
    private static final int STATUS = 222;
    private static final int START = 444;
    private static final int STOP = 666;

    public static final String STAT = "sega_ready\r\n";
    public static final String STARTED = "started\r\n";
    public static final String STOPPED = "stopped\r\n";

    private SerialPort serialPort;
    private boolean isConnected;
    
    public Connection(String portName) {
        serialPort = new SerialPort(portName);
    }

    public void open() throws SerialPortException {
        //Открываем порт
        serialPort.openPort();
        //Выставляем параметры
        serialPort.setParams(SerialPort.BAUDRATE_19200,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);
        //Назначаем слушателя события
        serialPort.addEventListener(new SerialPortEventListener() {
            @Override
            public void serialEvent(SerialPortEvent serialPortEvent) {
                System.out.println("Serial event occured!");
                String data;
                try {
                    if ((data = serialPort.readString()) != null) {
                        System.out.println("Port response: " + data);
                        if (data.equals(STAT)) {
                            setChanged();
                            notifyObservers(data);
                        } else if (data.equals(STARTED)) {
                            System.out.println("Started: " + data);
                        } else if (data.equals(STOPPED)) {
                            System.out.println("Stopped: " + data);
                        } else {
                            //gamepads data
                            System.out.println("Respose: " + data);
//                    String buf;
//                    String[] raw;
//                    int code_1, code_2;
//                    int i;

//                        try {
//                            if ((buf = connection.readString()) != null) {
//                                if (buf.contains(" ")) {
//                                    raw = buf.split(" ");
//
//                                    code_1 = raw[0].isEmpty() ? 0 : Integer.parseInt(raw[0]);
//                                    code_2 = raw[1].isEmpty() ? 0 : Integer.parseInt(raw[1].trim());
//
//                                    for (i = 0; i < CODE.length; i++) {
//                                        if ((code_1 & CODE[i]) == CODE[i]) {
//                                            robot.keyPress(currentKeyMap[i][0]);
//                                            System.out.println("Joy 1: press-" + currentKeyMap[i][0]);
//                                            ((Display) jPanel1).press(0, i);
//                                        } else if (((Display) jPanel1).isPressed(0, i)) {
//                                            robot.keyRelease(currentKeyMap[i][0]);
//                                            System.out.println("Joy 1: release-" + currentKeyMap[i][0]);
//                                            ((Display) jPanel1).release(0, i);
//                                        }
//
//                                        if ((code_2 & CODE[i]) == CODE[i]) {
//                                            robot.keyPress(currentKeyMap[i][1]);
//                                            System.out.println("Joy 2: press-" + currentKeyMap[i][1]);
//                                            ((Display) jPanel1).press(1, i);
//                                        } else if (((Display) jPanel1).isPressed(1, i)) {
//                                            robot.keyRelease(currentKeyMap[i][1]);
//                                            System.out.println("Joy 2: release-" + currentKeyMap[i][1]);
//                                            ((Display) jPanel1).release(1, i);
//                                        }
//                                    }
//                                    jPanel1.repaint();
//                                }
//                            }
//                        } catch (NumberFormatException ex) {
//                            System.out.println(ex);
//                        } catch (SerialPortException ex) {
//                            System.out.println(ex);
//                        }
                        }
                    }
                } catch (SerialPortException ex) {
                    //can't read string from stream
                    System.err.println("Read from stream error!\n" + ex.getMessage());
                }
            }
        }, SerialPort.MASK_RXCHAR);
        //send beacon request
        //serialPort.writeInt(STATUS);
    }

    @SuppressWarnings("CallToPrintStackTrace")
    public void close() {
        try {
            if (serialPort.isOpened()) {
                serialPort.closePort();
            }
        } catch (SerialPortException ex) {
            ex.printStackTrace();
        }
    }
    
    public boolean isOpened() {
        return serialPort.isOpened();
    }
    
    public void setObserver(Observer observer) {
        addObserver(observer);
    }
    
    public void send(String code) {
        try {
            serialPort.writeString(code);
        } catch (SerialPortException ex) {
            System.err.println("Write error!\n" + ex.getMessage());
        }
    }
}
