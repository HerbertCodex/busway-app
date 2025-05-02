package ci.justapp.busway.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ci.justapp.busway.data.local.entities.TransportLineEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for interacting with the transport_lines table.
 *
 * Provides basic CRUD operations to manage transport lines in the local database.
 */
@Dao
interface TransportLineDao {

    @Query("SELECT * FROM transport_lines ORDER BY slug ASC")
    fun findMany(): Flow<List<TransportLineEntity>>

    @Query("SELECT * FROM transport_lines WHERE slug = :slug")
    suspend fun findBySlug(slug: String): TransportLineEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(line: TransportLineEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMany(lines: List<TransportLineEntity>)

    @Update
    suspend fun update(line: TransportLineEntity)

    @Delete
    suspend fun delete(line: TransportLineEntity)
}
