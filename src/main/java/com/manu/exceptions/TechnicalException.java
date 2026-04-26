package com.manu.exceptions;

/**
 * Represents a technical error that occurs during the execution of an operation.
 *
 * <p>This exception is intended to capture unexpected or system-related issues,
 * such as I/O errors, network failures, or other runtime problems that are not
 * directly caused by business logic.</p>
 *
 * <p>It can be wrapped inside a {@link com.manu.result.Failure} to propagate
 * the error through the {@code Result} pattern.</p>
 */
public class TechnicalException extends Exception {

    /**
     * Creates a new {@code TechnicalException} with the specified detail message.
     *
     * @param message a description of the technical error
     */
    public TechnicalException(String message) {
        super(message);
    }
}
