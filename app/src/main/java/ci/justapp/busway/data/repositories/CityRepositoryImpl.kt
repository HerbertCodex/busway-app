package ci.justapp.busway.data.repositories

import androidx.annotation.WorkerThread
import ci.justapp.busway.data.local.dao.CityDao
import ci.justapp.busway.data.local.entities.CityEntity
import ci.justapp.busway.domain.models.CityModel
import ci.justapp.busway.domain.repositories.CityRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Implementation of the [CityRepository] interface.
 *
 * This class handles data operations related to cities, interacting with the [CityDao] to perform
 * database actions. It provides methods for retrieving, inserting, updating, and deleting city data.
 *
 * @property cityDao The Data Access Object for cities, providing access to the underlying database.
 */
class CityRepositoryImpl @Inject constructor(private val cityDao: CityDao) : CityRepository {

    override fun getCities(): Flow<List<CityModel>> {
        return cityDao.findMany().map { cities -> cities.map { it.toModel() } }
    }

    override suspend fun getCityBySlug(slug: String): CityModel? {
        return cityDao.findBySlug(slug)?.toModel()
    }

    @WorkerThread
    override suspend fun insert(city: CityModel) {
        return cityDao.insert(city.toEntity())
    }

    @WorkerThread
    override suspend fun insertMany(cities: List<CityModel>) {
        return cityDao.insertMany(cities.map { it.toEntity() })
    }

    @WorkerThread
    override suspend fun update(city: CityModel) {
        return cityDao.update(city.toEntity())
    }

    @WorkerThread
    override suspend fun delete(city: CityModel) {
        return cityDao.delete(city.toEntity())
    }

    private fun CityEntity.toModel(): CityModel {
        return CityModel(
            id = id,
            name = name,
            slug = slug,
            countryId = countryId,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    private fun CityModel.toEntity(): CityEntity {
        return CityEntity(
            id = id,
            name = name,
            slug = slug,
            countryId = countryId,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}