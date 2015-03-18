package pongline.display;

import java.awt.Toolkit;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.PlainDocument;

/**
 * A document for a text component that only accepts integer values.
 *
 * @author adam
 * @date May 26, 2012
 */
public class LimitedLengthIntegerDocument extends PlainDocument {

    /** The maximum number of digits that are allowed in this document. */
    private final int m_maxDigits;

    /**
     * Constructor.  Creates a new IntegerDocument. This document does not limit length, but does restrict
     * input to only integral values.
     */
    public LimitedLengthIntegerDocument(){
        this(-1);
    }

    /**
     * Constructor.  Creates a new IntegerDocument with the specified number of digits.  If more digits beyond this
     * value are inserted, they will be ignored and a system beep will occur.
     * @param maxDigits
     */
    public LimitedLengthIntegerDocument(int maxDigits){
        super();
        m_maxDigits = maxDigits;
    }

    /**
     * {@inheritDoc}
     * Beeps if any non integer value is attempted to be inserted into this document.
     */
    @Override
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException{
        if (str != null) {
            try {
                // Ensure it is a valid Integer.
                Integer.valueOf(str);
                
                // If a max length has been specified...
                if (m_maxDigits > 0){

                    // Check if the new insert will extend beyond the max allowable length.
                    if (getLength() + str.length() > m_maxDigits){
                        Toolkit.getDefaultToolkit().beep();
                    } else {
                        super.insertString(offs, str, a);
                    }
                } else {
                    // If no length was specified, insert the string.
                    super.insertString(offs, str, a);
                }
            } catch (NumberFormatException nfe){
                Toolkit.getDefaultToolkit().beep();
            }
        }
    }
}