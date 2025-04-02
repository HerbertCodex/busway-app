package ci.justapp.busway.data.local.repository

import androidx.annotation.WorkerThread
import ci.justapp.busway.data.local.dao.TransportTypeDao
import ci.justapp.busway.data.local.entities.TransportTypeEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository for accessing transport types from the local Room database.
 *
 * A transport type defines a specific kind of vehicle (e.g., Express Bus, Minivan),
 * linked to both a transport company and a mode.
 */
class TransportTypeRepository(private val typeDao: TransportTypeDao) {

    // Real-time observation of all transport types
    val allTypes: Flow<List<TransportTypeEntity>> = typeDao.getAllFlow()

    @WorkerThread
    suspend fun insert(type: TransportTypeEntity) {
        typeDao.insert(type)
    }

    @WorkerThread
    suspend fun insertAll(types: List<TransportTypeEntity>) {
        typeDao.insertAll(types)
    }

    @WorkerThread
    suspend fun update(type: TransportTypeEntity) {
        typeDao.update(type)
    }

    @WorkerThread
    suspend fun delete(type: TransportTypeEntity) {
        typeDao.delete(type)
    }

    suspend fun getAll(): List<TransportTypeEntity> = typeDao.getAll()

    suspend fun getBySlug(slug: String): TransportTypeEntity? = typeDao.getBySlug(slug)
}
