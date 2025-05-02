package ci.justapp.busway.domain.models

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
    val createdAt: Long,
    val updatedAt: Long
)
