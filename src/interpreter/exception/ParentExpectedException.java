package interpreter.exception;

public class ParentExpectedException extends Exception {

    /**
     * Creates a new instance of <code>ParentExpectedException</code> without detail
     * message.
     */
    public ParentExpectedException() {
    }

    /**
     * Constructs an instance of <code>ParentExpectedException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ParentExpectedException(String msg) {
        super(msg);
    }
}
