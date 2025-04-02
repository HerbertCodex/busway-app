package ci.justapp.busway.data.repository

import androidx.annotation.WorkerThread
import ci.justapp.busway.data.local.dao.CommuneDao
import ci.justapp.busway.data.local.entities.CommuneEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository for accessing commune data from the local Room database.
 *
 * Provides an abstraction over the [CommuneDao] and centralizes all
 * commune-related data operations.
 */
class CommuneRepository(private val communeDao: CommuneDao) {

    val allCommunes: Flow<List<CommuneEntity>> = communeDao.getAllFlow()


    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    suspend fun insert(commune: CommuneEntity) {
        communeDao.insert(commune)
    }

    @WorkerThread
    suspend fun insertAll(communes: List<CommuneEntity>) {
        communeDao.insertAll(communes)
    }

    @WorkerThread
    suspend fun update(commune: CommuneEntity) {
        communeDao.update(commune)
    }

    @WorkerThread
    suspend fun delete(commune: CommuneEntity) {
        communeDao.delete(commune)
    }

    suspend fun getAll(): List<CommuneEntity> = communeDao.getAll()

    suspend fun getBySlug(slug: String): CommuneEntity? = communeDao.getBySlug(slug)
}
