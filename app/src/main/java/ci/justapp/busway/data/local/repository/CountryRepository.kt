package ci.justapp.busway.data.repository

import androidx.annotation.WorkerThread
import ci.justapp.busway.data.local.dao.CountryDao
import ci.justapp.busway.data.local.entities.CountryEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository for accessing country data from the local Room database.
 *
 * Provides suspend-based and Flow-based access to country data.
 */
class CountryRepository(private val countryDao: CountryDao) {

    // Flow to observe countries in real-time (ideal for UI)
    val allCountries: Flow<List<CountryEntity>> = countryDao.getAllFlow()

    @WorkerThread
    suspend fun insert(country: CountryEntity) {
        countryDao.insert(country)
    }

    @WorkerThread
    suspend fun insertAll(countries: List<CountryEntity>) {
        countryDao.insertAll(countries)
    }

    @WorkerThread
    suspend fun update(country: CountryEntity) {
        countryDao.update(country)
    }

    @WorkerThread
    suspend fun delete(country: CountryEntity) {
        countryDao.delete(country)
    }

    suspend fun getAll(): List<CountryEntity> = countryDao.getAll()

    suspend fun getBySlug(slug: String): CountryEntity? = countryDao.getBySlug(slug)
}
