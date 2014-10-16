package interpreter.exception;

public class UnexpectedOperatorException extends Exception{
    /**
     * Creates a new instance of DerivateOperatorExpectedException without detail
     * message.
     */
    public UnexpectedOperatorException() {
    }

    /**
     * Constructs an instance of DerivateOperatorExpectedException with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public UnexpectedOperatorException(String msg) {
        super(msg);
    }
}
