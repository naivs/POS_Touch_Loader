import jssc.SerialPort;
import jssc.SerialPortException;

/**
 *
 * @author ivan
 */
public class COMConnection extends SerialPort {
    
    public COMConnection(String serialPortName) throws SerialPortException {
        super(serialPortName);

        //Открываем порт
        openPort();
        //Выставляем параметры
        setParams(SerialPort.BAUDRATE_9600,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);
    }
}
