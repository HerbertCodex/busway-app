package ci.justapp.busway.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Represents metadata about the dataset versioning in the system.
 *
 * This entity is stored in the "data_metadata" table within the Room database.
 * It tracks which version of data is currently stored locally, along with
 * the timestamp of the last update.
 *
 * This table can be used to determine whether new data should be fetched from
 * a server or other source.
 *
 * @property id A unique identifier for the metadata entry (e.g., "global", "sync_key", etc).
 * @property lastVersion The current version of the data.
 * @property lastUpdatedAt Timestamp (in millis) of the last update applied to the dataset.
 */
@Entity(tableName = "data_metadata")
data class DataMetadataEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "last_version")
    val lastVersion: Int,

    @ColumnInfo(name = "last_updated_at")
    val lastUpdatedAt: Long
)
