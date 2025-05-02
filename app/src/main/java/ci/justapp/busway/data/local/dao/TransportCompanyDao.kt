package ci.justapp.busway.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ci.justapp.busway.data.local.entities.TransportCompanyEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO for interacting with the transport_companies table.
 *
 * Provides CRUD operations for managing transport companies
 * and their association with countries.
 */
@Dao
interface TransportCompanyDao {

    @Query("SELECT * FROM transport_companies ORDER BY name ASC")
    fun findMany(): Flow<List<TransportCompanyEntity>>

    @Query("SELECT * FROM transport_companies WHERE slug = :slug")
    suspend fun findBySlug(slug: String): TransportCompanyEntity?


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(company: TransportCompanyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMany(companies: List<TransportCompanyEntity>)

    @Update
    suspend fun update(company: TransportCompanyEntity)

    @Delete
    suspend fun delete(company: TransportCompanyEntity)
}
