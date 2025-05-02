package ci.justapp.busway.domain.models

/**
 * Represents a commune (a type of administrative division) within a city.
 *
 * This data class holds information about a specific commune, including its unique identifier, name,
 * URL-friendly slug, official code, the ID of the city it belongs to, and timestamps for creation and updates.
 *
 * @property id The unique identifier of the commune.
 * @property name The name of the commune.
 * @property slug A URL-friendly representation of the commune's name (e.g., "my-commune-name").
 * @property code The official code assigned to the commune.
 * @property cityId The unique identifier of the city to which this commune belongs.
 * @property createdAt The timestamp (in milliseconds since the epoch) when the commune record was created.
 * @property updatedAt The timestamp (in milliseconds since the epoch) when the commune record was last updated.
 */
data class CommuneModel(
    val id: String,
    val name: String,
    val slug: String,
    val code: String,
    val cityId: String,
    val createdAt: Long,
    val updatedAt: Long
)
