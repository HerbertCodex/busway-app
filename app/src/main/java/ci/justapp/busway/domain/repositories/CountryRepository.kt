package ci.justapp.busway.domain.repositories

import ci.justapp.busway.domain.models.CountryModel
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining the contract for interacting with country data.
 * This repository handles operations related to fetching, inserting, updating, and deleting
 * country information, abstracting the data source from the rest of the application.
 */
interface CountryRepository {
    fun getCountries(): Flow<List<CountryModel>>
    suspend fun getCountryBySlug(slug: String): CountryModel?
    suspend fun insert(country: CountryModel)
    suspend fun insertMany(countries: List<CountryModel>)
    suspend fun update(country: CountryModel)
    suspend fun delete(country: CountryModel)
}