package ci.justapp.busway.domain.models

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
    val createdAt: Long,
    val updatedAt: Long
)