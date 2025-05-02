package ci.justapp.busway.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ci.justapp.busway.data.local.entities.CommuneEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the CommuneEntity.
 *
 * Provides basic CRUD operations for accessing communes in the local database.
 */
@Dao
interface CommuneDao {

    @Query("SELECT * FROM communes ORDER BY name ASC")
    fun findMany(): Flow<List<CommuneEntity>>

    @Query("SELECT * FROM communes WHERE slug = :slug")
    suspend fun findBySlug(slug: String): CommuneEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(commune: CommuneEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMany(communes: List<CommuneEntity>)

    @Update
    suspend fun update(commune: CommuneEntity)

    @Delete
    suspend fun delete(commune: CommuneEntity)
}
