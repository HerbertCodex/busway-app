package ci.justapp.busway.data.local.repository

import androidx.annotation.WorkerThread
import ci.justapp.busway.data.local.dao.CityDao
import ci.justapp.busway.data.local.entities.CityEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository for accessing city data from the local Room database.
 *
 * Provides an abstraction over the [CityDao] and centralizes all
 * access to city data.
 */
class CityRepository(private val cityDao: CityDao) {

    val allCities: Flow<List<CityEntity>> = cityDao.getAllFlow()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(city: CityEntity) {
        cityDao.insert(city)
    }

    @WorkerThread
    suspend fun insertAll(cities: List<CityEntity>) {
        cityDao.insertAll(cities)
    }

    @WorkerThread
    suspend fun update(city: CityEntity) {
        cityDao.update(city)
    }

    @WorkerThread
    suspend fun delete(city: CityEntity) {
        cityDao.delete(city)
    }

    suspend fun getAll(): List<CityEntity> = cityDao.getAll()

    suspend fun getBySlug(slug: String): CityEntity? = cityDao.getBySlug(slug)
}
