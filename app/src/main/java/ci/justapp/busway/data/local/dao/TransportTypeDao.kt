package ci.justapp.busway.data.local.dao

import androidx.room.*
import ci.justapp.busway.data.local.entities.TransportTypeEntity

/**
 * DAO for interacting with the transport_types table.
 *
 * Provides CRUD operations for managing transport types,
 * which are linked to transport modes and companies.
 */
@Dao
interface TransportTypeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(type: TransportTypeEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(types: List<TransportTypeEntity>)

    @Update
    suspend fun update(type: TransportTypeEntity)

    @Delete
    suspend fun delete(type: TransportTypeEntity)

    @Query("SELECT * FROM transport_types WHERE slug = :slug")
    suspend fun getBySlug(slug: String): TransportTypeEntity?

    @Query("SELECT * FROM transport_types")
    suspend fun getAll(): List<TransportTypeEntity>

    @Query("SELECT * FROM transport_types")
    fun getAllFlow(): kotlinx.coroutines.flow.Flow<List<TransportTypeEntity>>
}
