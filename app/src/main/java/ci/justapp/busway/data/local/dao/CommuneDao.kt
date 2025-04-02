package ci.justapp.busway.data.local.dao

import androidx.room.*
import ci.justapp.busway.data.local.entities.CommuneEntity

/**
 * Data Access Object for the CommuneEntity.
 *
 * Provides basic CRUD operations for accessing communes in the local database.
 */
@Dao
interface CommuneDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(commune: CommuneEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(communes: List<CommuneEntity>)

    @Update
    suspend fun update(commune: CommuneEntity)

    @Delete
    suspend fun delete(commune: CommuneEntity)

    @Query("SELECT * FROM communes WHERE slug = :slug")
    suspend fun getBySlug(slug: String): CommuneEntity?

    @Query("SELECT * FROM communes")
    suspend fun getAll(): List<CommuneEntity>

    @Query("SELECT * FROM communes")
    fun getAllFlow(): kotlinx.coroutines.flow.Flow<List<CommuneEntity>>
}
