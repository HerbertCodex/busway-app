package ci.justapp.busway.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

/**
 * Represents a country entity in the system.
 *
 * This entity is stored in the "countries" table within the Room database.
 * It contains information about a specific country, such as its name, slug,
 * and international code.
 *
 * Constraints:
 * - **Index:** An index is created on the `name` column to improve query performance
 *   and enforce uniqueness of country names.
 *
 * @property id The unique identifier for the country (UUID as string).
 * @property name The name of the country.
 * @property slug A URL-friendly version of the name.
 * @property code A short international code (e.g. ISO 3166-1 alpha-2).
 * @property createdAt Timestamp of creation in milliseconds.
 * @property updatedAt Timestamp of last update in milliseconds.
 */
@Entity(
    tableName = "countries",
    indices = [Index(value = ["name"], unique = true)]
)
data class CountryEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "slug")
    val slug: String,

    @ColumnInfo(name = "code")
    val code: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis(),
)
