package serial;

import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;

/**
 *
 * @author ivan
 */
public class ConnectionImpl extends Connection {

    private final SerialPort serialPort;

    public ConnectionImpl(String portName) {
        serialPort = new SerialPort(portName);
    }

    @Override
    public void open() throws SerialPortException {
        //Открываем порт
        serialPort.openPort();
        //Выставляем параметры
        serialPort.setParams(SerialPort.BAUDRATE_19200,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);
        //Назначаем слушателя события
        serialPort.addEventListener((SerialPortEvent serialPortEvent) -> {
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
        }, SerialPort.MASK_RXCHAR);
        //validate();
    }

    @Override
    public boolean isOpen() {
        return serialPort.isOpened();
    }

    @Override
    public void send(String code) {
        try {
            serialPort.writeString(code);
        } catch (SerialPortException ex) {
            System.err.println("Write error!\n" + ex.getMessage());
        }
    }

    @SuppressWarnings("CallToPrintStackTrace")
    @Override
    public void close() {
        try {
            if (serialPort.isOpened()) {
                serialPort.writeString(RQ_STOP);
                serialPort.closePort();
            }
        } catch (SerialPortException ex) {
            ex.printStackTrace();
        }
    }
}
