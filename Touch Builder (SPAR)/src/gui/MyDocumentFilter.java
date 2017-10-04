package gui;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

/**
 *
 * @author Ivan
 */
public class MyDocumentFilter extends DocumentFilter {

    private int limit;

    public MyDocumentFilter() {
        limit = -1;
    }

    public MyDocumentFilter(int limit) {
        this.limit = limit;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
        string = string.replaceAll("[^0-9&&[^ ]]", "");
        super.insertString(fb, offset, string, attr);
    }

    @Override
    public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
        text = text.replaceAll("[^0-9&&[^ ]]", "");
        super.replace(fb, offset, length, text, attrs);
    }
}
