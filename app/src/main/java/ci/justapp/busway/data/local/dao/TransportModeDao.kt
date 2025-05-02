package ci.justapp.busway.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ci.justapp.busway.data.local.entities.TransportModeEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for interacting with the transport_modes table.
 *
 * Provides basic CRUD operations to manage modes of transport (e.g., Bus, Train).
 */
@Dao
interface TransportModeDao {

    @Query("SELECT * FROM transport_modes ORDER BY name")
    fun findMany(): Flow<List<TransportModeEntity>>

    @Query("SELECT * FROM transport_modes WHERE slug = :slug")
    suspend fun findBySlug(slug: String): TransportModeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(mode: TransportModeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMany(modes: List<TransportModeEntity>)

    @Update
    suspend fun update(mode: TransportModeEntity)

    @Delete
    suspend fun delete(mode: TransportModeEntity)
}
