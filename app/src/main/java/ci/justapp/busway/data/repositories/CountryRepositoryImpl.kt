package ci.justapp.busway.data.repositories

import android.util.Log
import androidx.annotation.WorkerThread
import ci.justapp.busway.data.local.dao.CountryDao
import ci.justapp.busway.data.local.entities.CountryEntity
import ci.justapp.busway.data.remote.services.CountriesApiService
import ci.justapp.busway.domain.models.CountryModel
import ci.justapp.busway.domain.repositories.CountryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import kotlin.collections.map

/**
 * Implementation of the [CountryRepository] interface.
 *
 * This class handles data access for [CountryModel] objects, interacting with the underlying
 * [CountryDao] to perform database operations. It provides methods to retrieve, insert, update,
 * and delete country data.
 *
 * @property countryDao The Data Access Object used to interact with the country data in the database.
 */
class CountryRepositoryImpl @Inject constructor(private val countryDao: CountryDao, private val apiService: CountriesApiService) :
    CountryRepository {

    override fun getCountries(): Flow<List<CountryModel>> {
        return countryDao.findMany().map { countries ->
            countries.map { it.toModel() }
        }
    }

    override suspend fun getCountryBySlug(slug: String): CountryModel? {
        return countryDao.findBySlug(slug)?.toModel()
    }

    @WorkerThread
    override suspend fun insert(country: CountryModel) {
        return countryDao.insert(country.toEntity())
    }

    @WorkerThread
    override suspend fun fetchAndStoreCountriesFromApi() {
        try {
            Log.d("CITY_API", "Appel à l'API Encore...")
            val response = apiService.getCountries()

            Log.d("CITY_API", "Réponse reçue. Total: ${response.total}, Nombre d'éléments: ${response.data.size}")


            Log.d("CITY_REPO", "Insertion en base de ${response.data.size} pays...")
            Log.d("data", "test: ${response.data.toString()}")
            insertMany(response.data)
            Log.d("CITY_REPO", "Insertion terminée avec succès.")
        } catch (e: Exception) {
            Log.e("CITY_API", "Erreur lors de la synchronisation : ${e.message}", e)
        }
    }


    @WorkerThread
    override suspend fun insertMany(countries: List<CountryModel>) {
        return countryDao.insertMany(countries.map { it.toEntity() })
    }

    @WorkerThread
    override suspend fun update(country: CountryModel) {
        return countryDao.update(country.toEntity())
    }

    @WorkerThread
    override suspend fun delete(country: CountryModel) {
        return countryDao.delete(country.toEntity())
    }

    private fun CountryEntity.toModel(): CountryModel {
        return CountryModel(
            id = id,
            name = name,
            slug = slug,
            codeIso = code,
            createdAt = createdAt.toString(),
            updatedAt = updatedAt.toString()
        )
    }

    private fun CountryModel.toEntity(): CountryEntity {
        return CountryEntity(
            id = id,
            name = name,
            slug = slug,
            code = codeIso,
            createdAt = getCreatedAtAsLong(),
            updatedAt = getUpdatedAtAsLong()
        )
    }
}