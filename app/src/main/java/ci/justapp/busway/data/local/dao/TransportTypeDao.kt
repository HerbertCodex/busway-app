package ci.justapp.busway.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ci.justapp.busway.data.local.entities.TransportTypeEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for interacting with the transport_types table.
 *
 * Provides CRUD operations for managing transport types,
 * which are linked to transport modes and companies.
 */
@Dao
interface TransportTypeDao {

    @Query("SELECT * FROM transport_types ORDER BY name ASC")
    fun findMany(): Flow<List<TransportTypeEntity>>

    @Query("SELECT * FROM transport_types WHERE slug = :slug")
    suspend fun findBySlug(slug: String): TransportTypeEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(type: TransportTypeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMany(types: List<TransportTypeEntity>)

    @Update
    suspend fun update(type: TransportTypeEntity)

    @Delete
    suspend fun delete(type: TransportTypeEntity)
}
