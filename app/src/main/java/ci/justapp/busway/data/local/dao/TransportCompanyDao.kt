package ci.justapp.busway.data.local.dao

import androidx.room.*
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

    @Query("SELECT * FROM transport_companies")
    fun getAllFlow(): Flow<List<TransportCompanyEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(company: TransportCompanyEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(companies: List<TransportCompanyEntity>)

    @Update
    suspend fun update(company: TransportCompanyEntity)

    @Delete
    suspend fun delete(company: TransportCompanyEntity)

    @Query("SELECT * FROM transport_companies WHERE slug = :slug")
    suspend fun getBySlug(slug: String): TransportCompanyEntity?

    @Query("SELECT * FROM transport_companies")
    suspend fun getAll(): List<TransportCompanyEntity>
}
