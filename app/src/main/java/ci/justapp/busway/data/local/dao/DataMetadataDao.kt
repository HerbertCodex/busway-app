package ci.justapp.busway.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ci.justapp.busway.data.local.entities.DataMetadataEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for interacting with the data_metadata table.
 *
 * This DAO provides CRUD operations for managing metadata
 * related to data versioning and synchronization.
 */
@Dao
interface DataMetadataDao {

    @Query("SELECT * FROM data_metadata ORDER BY last_updated_at DESC")
    fun findMany(): Flow<List<DataMetadataEntity>>

    @Query("SELECT * FROM data_metadata WHERE id = :id")
    suspend fun findById(id: String): DataMetadataEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(metadata: DataMetadataEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMany(metadataList: List<DataMetadataEntity>)

    @Update
    suspend fun update(metadata: DataMetadataEntity)

    @Delete
    suspend fun delete(metadata: DataMetadataEntity)
}
