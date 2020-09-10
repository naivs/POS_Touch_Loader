package serial;

import java.util.Observable;
import jssc.SerialPortException;

/**
 *
 * @author Ivan Naumov
 */
public abstract class Connection extends Observable {

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

    public abstract void open() throws SerialPortException;

    public abstract boolean isOpen();

    public abstract void send(String code);

    public abstract void close();
}
