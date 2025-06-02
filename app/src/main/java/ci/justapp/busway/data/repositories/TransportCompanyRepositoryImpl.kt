package ci.justapp.busway.data.repositories

import android.util.Log
import androidx.annotation.WorkerThread
import ci.justapp.busway.data.local.dao.TransportCompanyDao
import ci.justapp.busway.data.local.entities.TransportCompanyEntity
import ci.justapp.busway.data.remote.services.TransportCompanyApiService
import ci.justapp.busway.domain.models.TransportCompanyModel
import ci.justapp.busway.domain.repositories.TransportCompanyRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.Instant
import javax.inject.Inject

/**
 * Concrete implementation of the [TransportCompanyRepository] interface.
 *
 * This class handles the data layer operations related to transport companies,
 * interacting directly with the [TransportCompanyDao] to perform database
 * interactions. It provides methods to retrieve, insert, update, and delete
 * transport company data.
 *
 * @property transportCompanyDao The Data Access Object (DAO) for transport company entities.
 *     Injected via constructor dependency injection.
 */
class TransportCompanyRepositoryImpl @Inject constructor(private val transportCompanyDao: TransportCompanyDao,  private val apiService: TransportCompanyApiService) :
    TransportCompanyRepository {
    override fun getCompanies(): Flow<List<TransportCompanyModel>> {
        return transportCompanyDao.findMany().map { companies -> companies.map { it.toModel() } }
    }

    override suspend fun getCompanyBySlug(slug: String): TransportCompanyModel? {
        return transportCompanyDao.findBySlug(slug)?.toModel()
    }

    @WorkerThread
    override suspend fun insertMany(companies: List<TransportCompanyModel>) {
        transportCompanyDao.insertMany(companies.map { it.toEntity() })
    }

    @WorkerThread
    override suspend fun insert(company: TransportCompanyModel) {
        transportCompanyDao.insert(company.toEntity())
    }

    @WorkerThread
    override suspend fun delete(company: TransportCompanyModel) {
        transportCompanyDao.delete(company.toEntity())
    }

    @WorkerThread
    override suspend fun update(company: TransportCompanyModel) {
        transportCompanyDao.update(company.toEntity())
    }

    private fun TransportCompanyEntity.toModel(): TransportCompanyModel {
        return TransportCompanyModel(
            id = id,
            name = name,
            slug = slug,
            code = code,
            countryId = countryId,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    private fun TransportCompanyModel.toEntity(): TransportCompanyEntity {
        return TransportCompanyEntity(
            id = id,
            name = name,
            slug = slug,
            code = code,
            countryId = countryId,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    @WorkerThread
    override suspend fun fetchAndStoreTransportCompaniesFromApi() {
        try {
            Log.d("TC_API", "Appel API des compagnies de transport...")
            val response = apiService.getTransportCompanies()

            val companies = response.data.map { dto ->
                TransportCompanyModel(
                    id = dto.id,
                    name = dto.name,
                    slug = dto.slug,
                    code = dto.code,
                    countryId = dto.country_id,
                    createdAt = Instant.parse(dto.created_at).toEpochMilli(),
                    updatedAt = Instant.parse(dto.updated_at).toEpochMilli()
                )
            }

            Log.d("TC_API", "Insertion en base de ${companies.size} compagnies...")
            insertMany(companies)
            Log.d("TC_API", "Synchronisation des compagnies réussie.")
        } catch (e: Exception) {
            Log.e("TC_API", "Erreur lors de la synchronisation : ${e.message}", e)
        }
    }
}