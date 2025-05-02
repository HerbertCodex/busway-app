package ci.justapp.busway.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

/**
 * Represents a mode of transport in the system.
 *
 * This entity is stored in the "modes" table within the Room database.
 * It defines different modes of transportation such as bus, train, ferry, etc.
 *
 * Constraints:
 * - **Index:** An index is created on the `name` column to ensure each transport mode is unique
 *   and to optimize query performance.
 *
 * @property id The unique identifier for the transport mode (ULID).
 * @property name The name of the mode (e.g., "Bus", "Train").
 * @property slug A URL-friendly version of the mode name.
 * @property createdAt Timestamp of creation in milliseconds.
 * @property updatedAt Timestamp of last update in milliseconds.
 */
@Entity(
    tableName = "transport_modes",
    indices = [Index(value = ["name"], unique = true)]
)
data class TransportModeEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "slug")
    val slug: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis(),
)
