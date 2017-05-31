package org.uni.ruse.mse.exceptions;

/**
 * Thrown when there is error concerning security related issue.
 *
 * @author sinan
 */
public class BlogSecurityException extends RuntimeException {

    private static final long serialVersionUID = -5521484811607563128L;

    /**
     * Creates new security runtime exception.
     */
    public BlogSecurityException() {
	// default
    }

    /**
     * Creates new security runtime exception with the given message.
     *
     * @param message
     *            the message for the exception
     */
    public BlogSecurityException(String message) {
	super(message);
    }

    /**
     * Creates new security runtime exception with the caused exception.
     *
     * @param cause
     *            the caused exception
     */
    public BlogSecurityException(Throwable cause) {
	super(cause);
    }

    /**
     * Creates new security runtime exception with given message and caused
     * exception.
     *
     * @param message
     *            the message for the exception
     * @param cause
     *            the caused exception
     */
    public BlogSecurityException(String message, Throwable cause) {
	super(message, cause);
    }

}
