package ci.justapp.busway.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.github.f4b6a3.ulid.UlidCreator
import java.util.UUID

/**
 * Represents a city entity within a country.
 *
 * This entity is stored in the "cities" table within the Room database.
 * It contains details about a city, including its name, slug, and the country it belongs to.
 *
 * Constraints:
 * - **Foreign Key Constraint:** `country_id` references `id` in [CountryEntity],
 *   ensuring that each city is linked to a valid country.
 *   The deletion is restricted if cities still reference the country.
 *
 * - **Index:** Index created on `country_id` to optimize queries and enforce referential integrity.
 *
 * @property id The unique identifier for the city (ULID).
 * @property name The name of the city.
 * @property slug A URL-friendly version of the city name.
 * @property countryId The foreign key referencing the associated country.
 * @property createdAt Timestamp of creation in milliseconds.
 * @property updatedAt Timestamp of last update in milliseconds.
 *
 * @see CountryEntity
 */
@Entity(
    tableName = "cities",
    foreignKeys = [
        ForeignKey(
            entity = CountryEntity::class,
            parentColumns = ["id"],
            childColumns = ["country_id"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [Index(value = ["country_id"])]
)
data class CityEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String ,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "slug")
    val slug: String,

    @ColumnInfo(name = "country_id")
    val countryId: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis(),
)
