package com.manu.result;

/**
 * Represents a successful result of an operation.
 *
 * <p>This record wraps a value of type {@code T} and provides
 * functional-style methods to transform or chain results.</p>
 *
 * @param <T> the type of the value contained in this success
 */
public record Success<T>(T value) implements Result<T> {

    /**
     * Always returns {@code true} since this is a success.
     *
     * @return {@code true}
     */
    @Override
    public boolean isSuccess() { return true; }

    /**
     * Always returns {@code false} since this is not a failure.
     *
     * @return {@code false}
     */
    @Override
    public boolean isFailure() { return false; }

    /**
     * Applies a transformation function to the contained value.
     * The result is wrapped in a new {@code Success}.
     *
     * @param <U> the type of the new value
     * @param mapper function to transform the value
     * @return a new {@code Success} containing the transformed value
     */
    @Override
    public <U> Result<U> map(java.util.function.Function<? super T, ? extends U> mapper) {
        return new Success<>(mapper.apply(value));
    }

    /**
     * Applies a function that returns another {@code Result} to the contained value.
     * This allows chaining operations that may succeed or fail.
     *
     * @param <U> the type of the new value
     * @param mapper function that transforms the value into another {@code Result}
     * @return the {@code Result} returned by the function
     */
    @Override
    public <U> Result<U> flatMap(java.util.function.Function<? super T, Result<U>> mapper) {
        return mapper.apply(value);
    }
}
