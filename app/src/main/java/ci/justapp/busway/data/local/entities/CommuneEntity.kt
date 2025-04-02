package ci.justapp.busway.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

/**
 * Represents a commune entity within a city.
 *
 * This entity is stored in the "communes" table within the Room database.
 * It contains information about a specific commune, including its name,
 * slug, short code, and the city it belongs to.
 *
 * Constraints:
 * - **Foreign Key Constraint:** `city_id` references `id` in [CityEntity].
 *   Deletion is restricted if communes reference the city.
 *
 * - **Index:** A composite index is created on `city_id` and `name`
 *   to speed up filtering by city and avoid duplicate names in the same city.
 *
 * @property id Unique identifier for the commune (UUID as String).
 * @property name The name of the commune.
 * @property slug A URL-friendly version of the name.
 * @property code A short code (e.g., administrative or internal).
 * @property cityId The city this commune belongs to.
 * @property createdAt Timestamp of creation.
 * @property updatedAt Timestamp of last update.
 */
@Entity(
    tableName = "communes",
    foreignKeys = [
        ForeignKey(
            entity = CityEntity::class,
            parentColumns = ["id"],
            childColumns = ["city_id"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [Index(value = ["city_id", "name"], unique = true)]
)
data class CommuneEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "slug")
    val slug: String,

    @ColumnInfo(name = "code")
    val code: String,

    @ColumnInfo(name = "city_id")
    val cityId: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis(),
)
