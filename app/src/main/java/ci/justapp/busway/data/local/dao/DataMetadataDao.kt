package ci.justapp.busway.data.local.dao

import androidx.room.*
import ci.justapp.busway.data.local.entities.DataMetadataEntity

/**
 * DAO for interacting with the data_metadata table.
 *
 * This DAO provides CRUD operations for managing metadata
 * related to data versioning and synchronization.
 */
@Dao
interface DataMetadataDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(metadata: DataMetadataEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(metadataList: List<DataMetadataEntity>)

    @Update
    suspend fun update(metadata: DataMetadataEntity)

    @Delete
    suspend fun delete(metadata: DataMetadataEntity)

    @Query("SELECT * FROM data_metadata WHERE id = :id")
    suspend fun getById(id: String): DataMetadataEntity?

    @Query("SELECT * FROM data_metadata")
    suspend fun getAll(): List<DataMetadataEntity>

    @Query("SELECT * FROM data_metadata")
    fun getAllFlow(): kotlinx.coroutines.flow.Flow<List<DataMetadataEntity>>
}
