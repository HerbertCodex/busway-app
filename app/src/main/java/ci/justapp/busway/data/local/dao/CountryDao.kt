package ci.justapp.busway.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ci.justapp.busway.data.local.entities.CountryEntity
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the CountryEntity.
 *
 * Provides methods for inserting, updating, deleting, and querying countries.
 */
@Dao
interface CountryDao {

    @Query("SELECT * FROM countries ORDER BY name ASC")
    fun findMany(): Flow<List<CountryEntity>>

    @Query("SELECT * FROM countries WHERE slug = :slug")
    suspend fun findBySlug(slug: String): CountryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(country: CountryEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMany(countries: List<CountryEntity>)

    @Update
    suspend fun update(country: CountryEntity)

    @Delete
    suspend fun delete(country: CountryEntity)
}
