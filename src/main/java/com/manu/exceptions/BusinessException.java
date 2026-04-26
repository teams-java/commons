package com.manu.exceptions;

/**
 * Represents a business rule violation or domain-specific error.
 *
 * <p>This exception is intended to capture problems related to the
 * application's business logic, such as invalid input, rule violations,
 * or conditions that prevent a process from continuing according to
 * business requirements.</p>
 *
 * <p>It can be wrapped inside a {@link com.manu.result.Failure} to propagate
 * the error through the {@code Result} pattern.</p>
 */
public class BusinessException extends Exception {

    /**
     * Creates a new {@code BusinessException} with the specified detail message.
     *
     * @param message a description of the business error
     */
    public BusinessException(String message) {
        super(message);
    }
}
