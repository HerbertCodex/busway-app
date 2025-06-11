package ci.justapp.busway.domain.models

import java.time.Instant

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