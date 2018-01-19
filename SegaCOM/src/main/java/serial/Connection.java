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
    public static final String RQ_STAT = "222";
    public static final String RQ_START = "444";
    public static final String RQ_STOP = "666";

    public static final String RPL_STAT = "sega_ready\r\n";
    public static final String RPL_START = "started\r\n";
    public static final String RPL_STOP = "stopped\r\n";

    private final SerialPort serialPort;

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
                String data;
                try {
                    if ((data = serialPort.readString()) != null) {
                        //if (!data.equals(RQ_STAT)) {
                        setChanged();
                        notifyObservers(data);
                        //} else {
                        //System.err.println("In event: " + data);
                        //}
                    }
                } catch (SerialPortException ex) {
                    System.err.println("Error in SerialPortEvent!\n" + ex.getMessage());
                }
            }
        }, SerialPort.MASK_RXCHAR);
        //validate();
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
