package com.manu.result;

/**
 * Represents the outcome of an operation that can either succeed or fail.
 *
 * <p>This pattern allows explicit handling of success and failure cases
 * without relying on exceptions for normal control flow. It is useful for
 * operations that may return a valid value or a controlled failure.</p>
 *
 * <p>This sealed interface can only be implemented by
 * {@link Success} and {@link Failure}.</p>
 *
 * @param <T> the type of the value returned in case of success
 */
public sealed interface Result<T> permits Success, Failure {

    /**
     * Checks if this result represents a success.
     *
     * @return {@code true} if it is a success, {@code false} otherwise
     */
    boolean isSuccess();

    /**
     * Checks if this result represents a failure.
     *
     * @return {@code true} if it is a failure, {@code false} otherwise
     */
    boolean isFailure();

    /**
     * Applies a transformation function to the value if this result is a success.
     * If this result is a failure, the same failure is returned without applying the function.
     *
     * @param <U> the type of the new value
     * @param mapper function that transforms the success value
     * @return a new {@code Result} containing the transformed value, or the original failure
     */
    <U> Result<U> map(java.util.function.Function<? super T, ? extends U> mapper);

    /**
     * Applies a function that returns another {@code Result} to the value if this result is a success.
     * If this result is a failure, the same failure is returned without applying the function.
     *
     * @param <U> the type of the new value
     * @param mapper function that transforms the success value into another {@code Result}
     * @return the {@code Result} returned by the function, or the original failure
     */
    <U> Result<U> flatMap(java.util.function.Function<? super T, Result<U>> mapper);
}
