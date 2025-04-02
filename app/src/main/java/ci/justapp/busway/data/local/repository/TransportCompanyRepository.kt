package ci.justapp.busway.data.local.repository

import androidx.annotation.WorkerThread
import ci.justapp.busway.data.local.dao.TransportCompanyDao
import ci.justapp.busway.data.local.entities.TransportCompanyEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository for accessing transport company data from the local Room database.
 *
 * This class abstracts access to the DAO and can be extended later to include
 * remote data fetching logic.
 */
class TransportCompanyRepository(private val companyDao: TransportCompanyDao) {

    // Observed Flow: automatically updated when DB content changes
    val allCompanies: Flow<List<TransportCompanyEntity>> = companyDao.getAllFlow()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(company: TransportCompanyEntity) {
        companyDao.insert(company)
    }

    @WorkerThread
    suspend fun insertAll(companies: List<TransportCompanyEntity>) {
        companyDao.insertAll(companies)
    }

    @WorkerThread
    suspend fun delete(company: TransportCompanyEntity) {
        companyDao.delete(company)
    }

    @WorkerThread
    suspend fun update(company: TransportCompanyEntity) {
        companyDao.update(company)
    }

    suspend fun getBySlug(slug: String): TransportCompanyEntity? = companyDao.getBySlug(slug)
}
