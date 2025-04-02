package ci.justapp.busway.data.local.dao

import androidx.room.*
import ci.justapp.busway.data.local.entities.TransportLineEntity

/**
 * DAO for interacting with the transport_lines table.
 *
 * Provides basic CRUD operations to manage transport lines in the local database.
 */
@Dao
interface TransportLineDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(line: TransportLineEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(lines: List<TransportLineEntity>)

    @Update
    suspend fun update(line: TransportLineEntity)

    @Delete
    suspend fun delete(line: TransportLineEntity)

    @Query("SELECT * FROM transport_lines WHERE slug = :slug")
    suspend fun getBySlug(slug: String): TransportLineEntity?

    @Query("SELECT * FROM transport_lines")
    suspend fun getAll(): List<TransportLineEntity>

    @Query("SELECT * FROM transport_lines")
    fun getAllFlow(): kotlinx.coroutines.flow.Flow<List<TransportLineEntity>>

}
