package ci.justapp.busway.domain.models

/**
 * Represents a country with its essential details.
 *
 * This data class holds information about a country, including its unique identifier,
 * name, URL-friendly slug, ISO code, and creation/update timestamps.
 *
 * @property id The unique identifier of the country.
 * @property name The full name of the country.
 * @property slug A URL-friendly version of the country's name.
 * @property code The ISO code of the country (e.g., "US" for the United States).
 * @property createdAt The timestamp (in milliseconds) when the country record was created.
 * @property updatedAt The timestamp (in milliseconds) when the country record was last updated.
 */
data class CountryModel(
    val id: String,
    val name: String,
    val slug: String,
    val code: String,
    val createdAt: Long,
    val updatedAt: Long
)
