package com.manu.canonical;

/**
 * Represents the canonical header for a message or event in the system.
 *
 * <p>This record encapsulates metadata commonly required for message
 * processing, routing, and auditing. It provides a standardized structure
 * that can be reused across services.</p>
 *
 * @param messageId   unique identifier for the message
 * @param source      origin of the message (system or service name)
 * @param destination target system or service
 * @param eventType   type of event represented by the message
 * @param timestamp   time when the event occurred
 * @param userId      identifier of the user associated with the event
 * @param roles       roles or permissions of the user
 */
public record Header(
        String messageId,
        String source,
        String destination,
        String eventType,
        java.time.Instant timestamp,
        String userId,
        String roles
) { }
