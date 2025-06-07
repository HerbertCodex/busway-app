package ci.justapp.busway.domain.models

import kotlinx.serialization.Serializable
import java.time.Instant

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
    val codeIso: String,
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