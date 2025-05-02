package ci.justapp.busway.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import ci.justapp.busway.data.local.entities.CityEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CityDao {

    @Query("SELECT * FROM cities ORDER BY name ASC")
    fun findMany(): Flow<List<CityEntity>>

    @Query("SELECT * FROM cities WHERE slug = :slug")
    suspend fun findBySlug(slug: String): CityEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(city: CityEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMany(cities: List<CityEntity>)

    @Update
    suspend fun update(city: CityEntity)

    @Delete
    suspend fun delete(city: CityEntity)
}
