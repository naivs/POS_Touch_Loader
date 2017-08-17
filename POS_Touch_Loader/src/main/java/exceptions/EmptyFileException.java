package exceptions;

import java.io.IOException;

/**
 *
 * @author ivan
 */
public class EmptyFileException extends IOException {
    
    public EmptyFileException(String message) {
        super(message);
    }
}
