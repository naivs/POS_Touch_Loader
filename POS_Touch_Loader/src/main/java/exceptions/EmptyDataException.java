package exceptions;

import java.io.IOException;

/**
 *
 * @author ivan
 */
public class EmptyDataException extends IOException {
    
    public EmptyDataException(String message) {
        super(message);
    }
}
