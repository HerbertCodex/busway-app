package ci.justapp.busway.domain.models

import java.time.Instant

/**
 * Represents a transport mode with its associated details.
 *
 * This data class encapsulates the information about a specific transport mode,
 * including its unique identifier, display name, URL-friendly slug, and creation/update timestamps.
 *
 * @property id The unique identifier of the transport mode.
 * @property name The user-friendly name of the transport mode (e.g., "Bus", "Train", "Car").
 * @property slug A URL-friendly, lowercase string representation of the transport mode's name (e.g., "bus", "train", "car").
 * @property createdAt The timestamp (in milliseconds) when the transport mode record was created.
 * @property updatedAt The timestamp (in milliseconds) when the transport mode record was last updated.
 */
data class TransportModeModel(
    val id: String,
    val name: String,
    val slug: String,
    val createdAt: String, // Changé de Long à String
    val updatedAt: String
){
    /**
     * Converts the ISO 8601 createdAt string to milliseconds timestamp.
     */
    fun getCreatedAtAsLong(): Long {
        return Instant.parse(createdAt).toEpochMilli()
    }

    /**
     * Converts the ISO 8601 updatedAt string to milliseconds timestamp.
     */
    fun getUpdatedAtAsLong(): Long {
        return Instant.parse(updatedAt).toEpochMilli()
    }
}
