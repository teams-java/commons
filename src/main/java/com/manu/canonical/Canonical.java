package com.manu.canonical;

/**
 * Represents a canonical message structure consisting of a {@link Header}
 * and a payload of type {@code T}.
 *
 * <p>This record provides a standardized way to encapsulate both metadata
 * and business data for events or messages exchanged between systems.
 * The {@link Header} contains contextual information such as identifiers,
 * source, destination, and timestamp, while {@code data} carries the
 * actual domain-specific content.</p>
 *
 * @param <T> the type of the payload data
 * @param header the canonical header containing metadata
 * @param data   the payload or business content of the message
 */
public record Canonical<T>(Header header, T data) { }
