package ci.justapp.busway.domain.models

import java.time.Instant

/**
 * Represents a transport type within the system.
 *
 * This data class encapsulates information about a specific type of transport,
 * such as its unique identifier, name, slug, code, associated company and mode,
 * and timestamps for creation and last update.
 *
 * @property id The unique identifier for this transport type.
 * @property name The human-readable name of the transport type (e.g., "Bus", "Train").
 * @property slug A URL-friendly, lowercase representation of the transport type name (e.g., "bus", "train").
 * @property code A short, alphanumeric code representing the transport type (e.g., "BUS", "TRN").
 * @property companyId The ID of the company that provides this transport type.
 * @property modeId The ID of the transport mode this type belongs to (e.g., "road", "rail").
 * @property createdAt The timestamp (in milliseconds since the epoch) when this transport type was created.
 * @property updatedAt The timestamp (in milliseconds since the epoch) when this transport type was last updated.
 */
data class TransportTypeModel(
    val id: String,
    val name: String,
    val slug: String,
    val code: String,
    val companyId: String,
    val modeId: String,
    val createdAt: String,
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