package com.manu.command;

import com.manu.result.Result;

/**
 * Represents the Command pattern in a functional style.
 *
 * <p>A {@code Command} encapsulates an operation that can be executed
 * and returns a {@link Result} indicating success or failure. This
 * abstraction decouples the definition of an action from its execution,
 * making it easier to compose, store, and orchestrate commands.</p>
 *
 * <p>Example usage:</p>
 * <pre>{@code
 * Command<String> createUser = () -> new Success<>("user-123");
 * Result<String> result = createUser.execute();
 * }</pre>
 *
 * @param <T> the type of the value returned by the command in case of success
 */
@FunctionalInterface
public interface Command<T> {

    /**
     * Executes the operation encapsulated by the command.
     *
     * @return a {@link Result} containing the value if successful,
     *         or a failure wrapping an exception if the operation fails
     */
    Result<T> execute();
}
