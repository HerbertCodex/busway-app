package ci.justapp.busway.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.UUID

/**
 * Represents a specific transport type offered by a transport company under a certain mode.
 *
 * This entity is stored in the "transport_types" table within the Room database.
 * It describes a category of transport (e.g. "Express Bus", "Minivan") linked to both
 * a mode of transport and a transport company.
 *
 * Constraints:
 * - **Foreign Key Constraint 1:** `mode_id` references `id` in [TransportModeEntity].
 * - **Foreign Key Constraint 2:** `company_id` references `id` in [TransportCompanyEntity].
 *   Both deletions are restricted to preserve referential integrity.
 *
 * - **Index:** A composite index on `mode_id`, `company_id`, and `name` is created
 *   to improve lookup performance and enforce uniqueness.
 *
 * @property id Unique identifier for the transport type (UUID as String).
 * @property name The name of the transport type.
 * @property slug A URL-friendly version of the name.
 * @property code A short code (e.g., internal code, 3-letter).
 * @property companyId The ID of the transport company offering this type.
 * @property modeId The ID of the transport mode this type falls under.
 * @property createdAt Timestamp of creation.
 * @property updatedAt Timestamp of last update.
 */
@Entity(
    tableName = "transport_types",
    foreignKeys = [
        ForeignKey(
            entity = TransportModeEntity::class,
            parentColumns = ["id"],
            childColumns = ["mode_id"],
            onDelete = ForeignKey.RESTRICT
        ),
        ForeignKey(
            entity = TransportCompanyEntity::class,
            parentColumns = ["id"],
            childColumns = ["company_id"],
            onDelete = ForeignKey.RESTRICT
        )
    ],
    indices = [Index(value = ["mode_id", "company_id", "name"], unique = true)]
)
data class TransportTypeEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String,

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "slug")
    val slug: String,

    @ColumnInfo(name = "code")
    val code: String,

    @ColumnInfo(name = "company_id")
    val companyId: String,

    @ColumnInfo(name = "mode_id")
    val modeId: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis(),
)
