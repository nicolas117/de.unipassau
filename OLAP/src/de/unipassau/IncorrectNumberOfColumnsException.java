package de.unipassau;

public class IncorrectNumberOfColumnsException extends Exception {

    private static final long serialVersionUID = 1;

    public IncorrectNumberOfColumnsException() {
        super();
    }

    public IncorrectNumberOfColumnsException(String s) {
        super(s);
    }
}
