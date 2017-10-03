
import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import jssc.SerialPort;
import jssc.SerialPortException;

/**
 *
 * @author ivan
 */
public class SegaCOM {

    private static SerialPort serialPort;

    private static final int[] CODE = {
        1,
        2,
        4,
        16,
        32,
        64,
        128,
        512,
        4096,
        8192,
        16384,
        32768
    };

    private static final int[] KEY = {
        KeyEvent.VK_A,
        KeyEvent.VK_B,
        KeyEvent.VK_C,
        KeyEvent.VK_X,
        KeyEvent.VK_Y,
        KeyEvent.VK_Z,
        KeyEvent.VK_TAB,
        KeyEvent.VK_ENTER,
        KeyEvent.VK_UP,
        KeyEvent.VK_DOWN,
        KeyEvent.VK_LEFT,
        KeyEvent.VK_RIGHT
    };

    private static Robot robot;

    public static void main(String[] args) {

        try {
            robot = new Robot();
        } catch (AWTException e) {
            System.err.println("Can't create robot.\n" + e.getMessage());
        }

        serialPort = new SerialPort("/dev/ttyUSB0");
        try {
            //Открываем порт
            serialPort.openPort();
            //Выставляем параметры
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            String buf;
            int data;
            int i;

//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                System.err.println("Program unexpected interrupted.\n" + e.getMessage());
//            }
            while (serialPort.isOpened()) {
                if ((buf = serialPort.readString()) != null) {

                    System.out.print(buf);

                    buf = buf.substring(0, buf.length() - 2);

                    //if(buf.)
                    data = Integer.parseInt(buf);

                    for (i = 0; i < CODE.length; i++) {
                        if ((data & CODE[i]) == CODE[i]) {
                            robot.keyPress(KEY[i]);
                        } else {
                            robot.keyRelease(KEY[i]);
                        }
                    }
                }
            }
        } catch (SerialPortException ex) {
            System.out.println(ex);
        }
    }
}
