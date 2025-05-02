package ci.justapp.busway.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

/**
 * Represents a transport line in the system.
 *
 * This entity is stored in the "transport_lines" table within the Room database.
 * It contains information about a transport line operated by a company, using a given transport type,
 * within a specific city and between two communes.
 *
 * Constraints:
 * - **Foreign Key Constraints** link this line to:
 *   - [TransportCompanyEntity] via `company_id`
 *   - [TransportTypeEntity] via `type_transport_id`
 *   - [CityEntity] via `city_id`
 *   - [CommuneEntity] via `start_commune_id` and `end_commune_id`
 *   - [DataMetadataEntity] via `metadata_id`
 *
 * - **Index:** Composite indices to speed up queries, especially by company, city, and type.
 *
 * @property id UUID as string, primary key for the line.
 * @property slug A URL-friendly identifier.
 * @property line The name or label of the line.
 * @property lineNumber The official number/identifier of the line.
 * @property openingHours Operating hours of the line.
 * @property companyId The company operating the line.
 * @property typeTransportId The type of transport used.
 * @property cityId The city in which the line operates.
 * @property startCommuneId Start location (commune).
 * @property endCommuneId End location (commune).
 * @property geometry JSON structure (as string) of route geometry.
 * @property dataVersion Version number of the data.
 * @property syncedAt Timestamp of last sync.
 * @property metadataId Reference to metadata for sync.
 * @property createdAt Timestamp of creation.
 * @property updatedAt Timestamp of last update.
 */
@Entity(
    tableName = "transport_lines",
    foreignKeys = [
        ForeignKey(
            entity = TransportCompanyEntity::class,
            parentColumns = ["id"],
            childColumns = ["company_id"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = TransportTypeEntity::class,
            parentColumns = ["id"],
            childColumns = ["type_transport_id"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = CityEntity::class,
            parentColumns = ["id"],
            childColumns = ["city_id"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = CommuneEntity::class,
            parentColumns = ["id"],
            childColumns = ["start_commune_id"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = CommuneEntity::class,
            parentColumns = ["id"],
            childColumns = ["end_commune_id"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = DataMetadataEntity::class,
            parentColumns = ["id"],
            childColumns = ["metadata_id"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [
        Index(value = ["company_id"]),
        Index(value = ["type_transport_id"]),
        Index(value = ["city_id"]),
        Index(value = ["start_commune_id"]),
        Index(value = ["end_commune_id"]),
        Index(value = ["metadata_id"])
    ]
)
data class TransportLineEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "slug")
    val slug: String,

    @ColumnInfo(name = "line")
    val line: String,

    @ColumnInfo(name = "line_number")
    val lineNumber: String,

    @ColumnInfo(name = "opening_hours")
    val openingHours: String,

    @ColumnInfo(name = "company_id")
    val companyId: String,

    @ColumnInfo(name = "type_transport_id")
    val typeTransportId: String,

    @ColumnInfo(name = "city_id")
    val cityId: String,

    @ColumnInfo(name = "start_commune_id")
    val startCommuneId: String,

    @ColumnInfo(name = "end_commune_id")
    val endCommuneId: String,

    @ColumnInfo(name = "geometry")
    val geometry: String,

    @ColumnInfo(name = "data_version")
    val dataVersion: Int,

    @ColumnInfo(name = "synced_at")
    val syncedAt: Long,

    @ColumnInfo(name = "metadata_id")
    val metadataId: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis()
)
