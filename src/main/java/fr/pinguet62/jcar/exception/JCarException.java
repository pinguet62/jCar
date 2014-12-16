package fr.pinguet62.jcar.exception;

/** The base {@link RuntimeException} for this project. */
public class JCarException extends RuntimeException {

    private static final long serialVersionUID = 1;

    public JCarException() {
    }

    public JCarException(Throwable cause) {
        super(cause);
    }

}
