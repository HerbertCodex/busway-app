package ci.justapp.busway.domain.models

/**
 * Represents a transport company entity within the system.
 *
 * This data class encapsulates information about a transport company,
 * including its unique identifier, name, URL-friendly slug, internal code,
 * associated country, and timestamps for creation and last update.
 *
 * @property id The unique identifier for the transport company.
 * @property name The official name of the transport company.
 * @property slug A URL-friendly, human-readable identifier for the company, often used in routes.
 * @property code An internal code used to represent the transport company.
 * @property countryId The unique identifier of the country where the company is based or operates.
 * @property createdAt The timestamp (in milliseconds since the epoch) when the company record was created.
 * @property updatedAt The timestamp (in milliseconds since the epoch) when the company record was last updated.
 */
data class TransportCompanyModel(
    val id: String,
    val name: String,
    val slug: String,
    val code: String,
    val countryId: String,
    val createdAt: Long,
    val updatedAt: Long
)
