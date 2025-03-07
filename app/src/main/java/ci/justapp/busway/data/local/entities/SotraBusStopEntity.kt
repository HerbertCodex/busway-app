package ci.justapp.busway.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import com.github.f4b6a3.ulid.UlidCreator

/**
 * Represents a bus stop entity in the Sotra system.
 *
 * This entity is stored in the "sotra_bus_stops" table within the Room database.
 * It contains information about a specific bus stop, including its location,
 * category, and associated geometry.
 *
 * The table is designed with the following constraints:
 *  - **Foreign Key Constraint:** The `category_id` column references the `id` column
 *    in the `CategoryEntity` table. This ensures that each bus stop belongs to a valid category.
 *    The `onDelete` strategy is set to `RESTRICT`, meaning that a category cannot be deleted
 *    if there are bus stops associated with it.
 *  - **Index:** A composite index is created on `category_id` and `location` columns.
 *    This index improves query performance when searching for bus stops based on their
 *    category and location.
 *
 * @property id The unique identifier for the bus stop. Automatically generated using ULID.
 * @property categoryId The ID of the category to which this bus stop belongs.
 *                     This is a foreign key referencing `CategoryEntity`.
 * @property location A string representing the location description of the bus stop.
 * @property geometry An embedded object of type [GeometryEntity] containing the geometric
 *                    representation of the bus stop's location (e.g., latitude, longitude).
 * @property createdAt The timestamp (in milliseconds) when the bus stop record was created.
 *                    Defaults to the current system time.
 * @property updatedAt The timestamp (in milliseconds) when the bus stop record was last updated.
 *                    Defaults to the current system time.
 *
 * @see CategoryEntity
 * @see GeometryEntity
 */
@Entity(
    tableName = "sotra_bus_stops",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["category_id"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [Index(value = ["category_id", "location"])]
)
data class SotraBusStopEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = UlidCreator.getUlid().toString(),

    @ColumnInfo(name = "category_id")
    val categoryId: String,

    @ColumnInfo(name = "location")
    val location: String,

    @Embedded
    val geometry: GeometryEntity,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis(),
)
