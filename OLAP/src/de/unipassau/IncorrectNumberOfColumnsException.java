package de.unipassau;

/**
 * @author Nicolas Salgado
 * @version 1.0
 * <p>
 * Exception class
 */

public class IncorrectNumberOfColumnsException extends Exception {

    private static final long serialVersionUID = 1;

    /**
     * Constructor to get exception
     */
    public IncorrectNumberOfColumnsException() {
        super();
    }

    /**
     * Constructor to overload with String
     *
     * @param s message for exception
     */
    public IncorrectNumberOfColumnsException(String s) {
        super(s);
    }
}
