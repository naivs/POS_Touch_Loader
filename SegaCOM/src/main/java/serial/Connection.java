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
                //System.out.println("Serial event occured!");
                String data;
                try {
                    if ((data = serialPort.readString()) != null) {
                        //System.out.print("Respose: " + data);
                        setChanged();
                        notifyObservers(data);
                    }
                } catch (SerialPortException ex) {
                    //can't read string from stream
                    System.err.println("Error in SerialPortEvent!\n" + ex.getMessage());
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
