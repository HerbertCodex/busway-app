package ci.justapp.busway.data.local.dao

import androidx.room.*
import ci.justapp.busway.data.local.entities.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Query("SELECT * FROM cities")
    fun getAllFlow(): Flow<List<CityEntity>>


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(city: CityEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(cities: List<CityEntity>)

    @Update
    suspend fun update(city: CityEntity)

    @Delete
    suspend fun delete(city: CityEntity)

    @Query("SELECT * FROM cities WHERE slug = :slug")
    suspend fun getBySlug(slug: String): CityEntity?

    @Query("SELECT * FROM cities")
    suspend fun getAll(): List<CityEntity>
}
