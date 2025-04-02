package ci.justapp.busway.data.local.repository

import androidx.annotation.WorkerThread
import ci.justapp.busway.data.local.dao.DataMetadataDao
import ci.justapp.busway.data.local.entities.DataMetadataEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository for accessing data metadata (used for version tracking and sync state).
 *
 * Provides both Flow-based and suspend-based access methods.
 */
class DataMetadataRepository(private val metadataDao: DataMetadataDao) {

    val allMetadata: Flow<List<DataMetadataEntity>> = metadataDao.getAllFlow()

    @WorkerThread
    suspend fun insert(metadata: DataMetadataEntity) {
        metadataDao.insert(metadata)
    }

    @WorkerThread
    suspend fun insertAll(list: List<DataMetadataEntity>) {
        metadataDao.insertAll(list)
    }

    @WorkerThread
    suspend fun update(metadata: DataMetadataEntity) {
        metadataDao.update(metadata)
    }

    @WorkerThread
    suspend fun delete(metadata: DataMetadataEntity) {
        metadataDao.delete(metadata)
    }

    suspend fun getAll(): List<DataMetadataEntity> = metadataDao.getAll()

    suspend fun getById(id: String): DataMetadataEntity? = metadataDao.getById(id)
}
