package ci.justapp.busway.domain.models

/**
 * Represents a city in the domain layer.
 *
 * This model is used in the business logic and presentation layers.
 * It contains essential information about a city, such as its name, slug,
 * and the country it belongs to.
 *
 * @property id The unique identifier for the city (ULID as String).
 * @property name The name of the city.
 * @property slug A URL-friendly version of the city name.
 * @property countryId The identifier of the associated country.
 * @property createdAt Timestamp of creation in milliseconds.
 * @property updatedAt Timestamp of last update in milliseconds.
 */
data class CityModel(
    val id: String,
    val name: String,
    val slug: String,
    val countryId: String,
    val createdAt: Long,
    val updatedAt: Long
)
