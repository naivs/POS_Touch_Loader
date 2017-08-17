package utils;

import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import javax.swing.text.PlainDocument;

public class LimitedDocument extends PlainDocument {

    private int limit;

    public LimitedDocument() {
        limit = -1;
    }

    public LimitedDocument(int limit) {
        this.limit = limit;
    }
    
    public LimitedDocument(int limit, DocumentFilter documentFilter) {
        this.limit = limit;
        super.setDocumentFilter(documentFilter);
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {

        if (getLimit() < 0 || getLength() < getLimit()) {
            super.insertString(offs, str, a);
        }
    }

    @Override
    public void replace(int offset, int length, String text, AttributeSet attr) throws BadLocationException {

        if (getLimit() < 0 || getLength() < getLimit()) {
            super.replace(offset, length, text, attr);
        }
    }
}
