package ci.justapp.busway.domain.repositories

import ci.justapp.busway.domain.models.CityModel
import kotlinx.coroutines.flow.Flow

/**
 * Interface for interacting with city data.
 *
 * This repository provides methods to retrieve, insert, update, and delete city information.
 * It uses `Flow` for observing lists of cities and suspend functions for single city operations.
 */
interface CityRepository {
    fun getCities(): Flow<List<CityModel>>
    suspend fun getCityBySlug(slug: String): CityModel?
    suspend fun insert(city: CityModel)
    suspend fun insertMany(cities: List<CityModel>)
    suspend fun update(city: CityModel)
    suspend fun delete(city: CityModel)
}