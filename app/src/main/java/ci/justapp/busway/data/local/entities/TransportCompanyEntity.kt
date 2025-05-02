package ci.justapp.busway.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

/**
 * Represents a transport company operating within a country.
 *
 * This entity is stored in the "transport_companies" table within the Room database.
 * It contains details about a transport company such as its name, slug, code, and associated country.
 *
 * Constraints:
 * - **Foreign Key Constraint:** `country_id` references the `id` column of [CountryEntity],
 *   ensuring that each transport company is linked to a valid country.
 *   Deletion is restricted if companies reference the country.
 *
 * - **Index:** An index is created on `country_id` and `name` to speed up lookups and ensure uniqueness.
 *
 * @property id The unique identifier for the transport company (UUID as string).
 * @property name The name of the company.
 * @property slug A URL-friendly version of the company name.
 * @property code A short internal or external identifier code.
 * @property countryId The ID of the country where the company operates.
 * @property createdAt Timestamp of creation.
 * @property updatedAt Timestamp of last update.
 *
 * @see CountryEntity
 */
@Entity(
    tableName = "transport_companies",
    foreignKeys = [
        ForeignKey(
            entity = CountryEntity::class,
            parentColumns = ["id"],
            childColumns = ["country_id"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [Index(value = ["country_id", "name"], unique = true)]
)
data class TransportCompanyEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "slug")
    val slug: String,

    @ColumnInfo(name = "code")
    val code: String,

    @ColumnInfo(name = "country_id")
    val countryId: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis(),
)
