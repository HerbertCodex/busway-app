package ci.justapp.busway.domain.repositories

import ci.justapp.busway.domain.models.TransportCompanyModel
import kotlinx.coroutines.flow.Flow

/**
 * Interface for interacting with the data layer concerning transport companies.
 * This interface defines methods for retrieving, inserting, updating, and deleting
 * transport company information.
 */
interface TransportCompanyRepository {
    fun getCompanies(): Flow<List<TransportCompanyModel>>
    suspend fun getCompanyBySlug(slug: String): TransportCompanyModel?
    suspend fun insertMany(companies: List<TransportCompanyModel>)
    suspend fun insert(company: TransportCompanyModel)
    suspend fun delete(company: TransportCompanyModel)
    suspend fun update(company: TransportCompanyModel)
}