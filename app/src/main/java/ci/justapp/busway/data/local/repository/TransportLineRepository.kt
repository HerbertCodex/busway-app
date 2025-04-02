package ci.justapp.busway.data.local.repository

import androidx.annotation.WorkerThread
import ci.justapp.busway.data.local.dao.TransportLineDao
import ci.justapp.busway.data.local.entities.TransportLineEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository for accessing transport line data from the local Room database.
 *
 * Provides both suspend-based and Flow-based access methods.
 */
class TransportLineRepository(private val lineDao: TransportLineDao) {

    val allLines: Flow<List<TransportLineEntity>> = lineDao.getAllFlow()

    @WorkerThread
    suspend fun insert(line: TransportLineEntity) {
        lineDao.insert(line)
    }

    @WorkerThread
    suspend fun insertAll(lines: List<TransportLineEntity>) {
        lineDao.insertAll(lines)
    }

    @WorkerThread
    suspend fun update(line: TransportLineEntity) {
        lineDao.update(line)
    }

    @WorkerThread
    suspend fun delete(line: TransportLineEntity) {
        lineDao.delete(line)
    }

    suspend fun getAll(): List<TransportLineEntity> = lineDao.getAll()

    suspend fun getBySlug(slug: String): TransportLineEntity? = lineDao.getBySlug(slug)
}
