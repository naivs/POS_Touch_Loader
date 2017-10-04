package gui;

import java.io.File;
import javax.swing.filechooser.*;

/**
 *
 * @author Ivan
 */
public class ImageFilter extends FileFilter {

    //Accept all directories and all gif files.
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }

        String extension = getExtension(f);
        if (extension != null) {
            if (extension.equals("gif")) {
                return true;
            } else {
                return false;
            }
        }

        return false;
    }

    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }

    //The description of this filter
    public String getDescription() {
        return "GIF файлы";
    }
}
