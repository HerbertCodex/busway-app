package ci.justapp.busway.data.local.dao

import androidx.room.*
import ci.justapp.busway.data.local.entities.CountryEntity

/**
 * Data Access Object for the CountryEntity.
 *
 * Provides methods for inserting, updating, deleting, and querying countries.
 */
@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(country: CountryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(countries: List<CountryEntity>)

    @Update
    suspend fun update(country: CountryEntity)

    @Delete
    suspend fun delete(country: CountryEntity)

    @Query("SELECT * FROM countries WHERE slug = :slug")
    suspend fun getBySlug(slug: String): CountryEntity?

    @Query("SELECT * FROM countries")
    suspend fun getAll(): List<CountryEntity>

    @Query("SELECT * FROM countries")
    fun getAllFlow(): kotlinx.coroutines.flow.Flow<List<CountryEntity>>
}
