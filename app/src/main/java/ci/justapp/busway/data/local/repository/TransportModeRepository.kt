package ci.justapp.busway.data.repository

import androidx.annotation.WorkerThread
import ci.justapp.busway.data.local.dao.TransportModeDao
import ci.justapp.busway.data.local.entities.TransportModeEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository for accessing transport modes from the local Room database.
 *
 * Handles modes like Bus, Train, Metro, etc. and exposes them through
 * Flow and suspendable methods.
 */
class TransportModeRepository(private val modeDao: TransportModeDao) {

    val allModes: Flow<List<TransportModeEntity>> = modeDao.getAllFlow()

    @WorkerThread
    suspend fun insert(mode: TransportModeEntity) {
        modeDao.insert(mode)
    }

    @WorkerThread
    suspend fun insertAll(modes: List<TransportModeEntity>) {
        modeDao.insertAll(modes)
    }

    @WorkerThread
    suspend fun update(mode: TransportModeEntity) {
        modeDao.update(mode)
    }

    @WorkerThread
    suspend fun delete(mode: TransportModeEntity) {
        modeDao.delete(mode)
    }

    suspend fun getAll(): List<TransportModeEntity> = modeDao.getAll()

    suspend fun getBySlug(slug: String): TransportModeEntity? = modeDao.getBySlug(slug)
}
