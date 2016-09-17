package com.egenchallenge.emulator.exception;

/**Can be used if any resource is not available. Not used in the project
 * For HTTP 404 errros
 */
@SuppressWarnings("serial")
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException() {
        super();
    }

    public ResourceNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(Throwable cause) {
        super(cause);
    }

}
