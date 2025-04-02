package ci.justapp.busway.data.local.dao

import androidx.room.*
import ci.justapp.busway.data.local.entities.TransportModeEntity

/**
 * DAO for interacting with the transport_modes table.
 *
 * Provides basic CRUD operations to manage modes of transport (e.g., Bus, Train).
 */
@Dao
interface TransportModeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mode: TransportModeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(modes: List<TransportModeEntity>)

    @Update
    suspend fun update(mode: TransportModeEntity)

    @Delete
    suspend fun delete(mode: TransportModeEntity)

    @Query("SELECT * FROM transport_modes WHERE slug = :slug")
    suspend fun getBySlug(slug: String): TransportModeEntity?

    @Query("SELECT * FROM transport_modes")
    suspend fun getAll(): List<TransportModeEntity>

    @Query("SELECT * FROM transport_modes")
    fun getAllFlow(): kotlinx.coroutines.flow.Flow<List<TransportModeEntity>>
}
