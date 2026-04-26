package com.manu.result;

/**
 * Represents a failed result of an operation.
 *
 * <p>This record wraps an {@link Exception} that describes the cause of failure.
 * It propagates the failure through functional-style methods without applying
 * any transformations.</p>
 *
 * @param <T> the type parameter of the expected value (unused in failure case)
 */
public record Failure<T>(Exception exception) implements Result<T> {

    /**
     * Always returns {@code false} since this is not a success.
     *
     * @return {@code false}
     */
    @Override
    public boolean isSuccess() { return false; }

    /**
     * Always returns {@code true} since this represents a failure.
     *
     * @return {@code true}
     */
    @Override
    public boolean isFailure() { return true; }

    /**
     * Returns the same failure without applying the mapper function.
     *
     * @param <U> the type of the new value
     * @param mapper function that would transform the value in case of success
     * @return a new {@code Failure} containing the same exception
     */
    @Override
    public <U> Result<U> map(java.util.function.Function<? super T, ? extends U> mapper) {
        return new Failure<>(exception);
    }

    /**
     * Returns the same failure without applying the mapper function.
     *
     * @param <U> the type of the new value
     * @param mapper function that would transform the value into another {@code Result} in case of success
     * @return a new {@code Failure} containing the same exception
     */
    @Override
    public <U> Result<U> flatMap(java.util.function.Function<? super T, Result<U>> mapper) {
        return new Failure<>(exception);
    }
}
