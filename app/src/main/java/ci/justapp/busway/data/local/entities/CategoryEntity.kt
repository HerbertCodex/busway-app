package ci.justapp.busway.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import com.github.f4b6a3.ulid.UlidCreator

/**
 * Represents a category entity in the database.
 *
 * This data class is annotated with Room annotations to define its structure and relationships within the database.
 * It stores information about a category, including its unique ID, name, creation timestamp, and last update timestamp.
 *
 * @property id The unique identifier for the category. It's a ULID (Universally Unique Lexicographically Sortable Identifier) generated automatically.
 *             It serves as the primary key for the database table.
 * @property name The name of the category. This value must be unique within the database.
 * @property createdAt The timestamp representing when the category was created. Stored as milliseconds since the epoch.
 * @property updatedAt The timestamp representing when the category was last updated. Stored as milliseconds since the epoch.
 *
 * @constructor Creates a CategoryEntity instance.
 *              The ID is automatically generated using UlidCreator if not provided.
 *              The createdAt and updatedAt timestamps are automatically set to the current time if not provided.
 *
 * @Entity Defines this class as a Room entity.
 * @param tableName Specifies the name of the table in the database where categories will be stored.
 * @param indices Defines indices for the table.
 *                  In this case, an index is created for the "name" column, and it's set as unique,
 *                  ensuring that no two categories can have the same name.
 *
 * @Index Defines an index for the specified column(s) in the database table.
 * @param value The name of the column(s) to index.
 * @param unique Specifies whether the index should enforce uniqueness.
 *
 * @PrimaryKey Denotes the primary key of the table.
 * @ColumnInfo Provides details about a column in the database table.
 * @param name The name of the column in the database.
 */
@Entity(tableName = "categories", indices = [Index(value = ["name"], unique = true)])
data class CategoryEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: String = UlidCreator.getUlid().toString(),

    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "created_at")
    val createdAt: Long = System.currentTimeMillis(),

    @ColumnInfo(name = "updated_at")
    val updatedAt: Long = System.currentTimeMillis(),
)
