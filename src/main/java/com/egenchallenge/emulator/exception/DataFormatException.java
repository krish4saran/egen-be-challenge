package com.egenchallenge.emulator.exception;

/**Can be used if any input from the sensor is not in the correct format. Not used in the project
 * for HTTP 400 errors
 */
@SuppressWarnings("serial")
public final class DataFormatException extends RuntimeException {
    public DataFormatException() {
        super();
    }

    public DataFormatException(String message, Throwable cause) {
        super(message, cause);
    }

    public DataFormatException(String message) {
        super(message);
    }

    public DataFormatException(Throwable cause) {
        super(cause);
    }
}