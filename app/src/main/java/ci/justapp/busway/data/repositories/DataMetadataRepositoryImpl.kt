package ci.justapp.busway.data.repositories

import android.util.Log
import androidx.annotation.WorkerThread
import ci.justapp.busway.data.local.dao.DataMetadataDao
import ci.justapp.busway.data.local.entities.DataMetadataEntity
import ci.justapp.busway.data.remote.services.DataMetadataApiService
import ci.justapp.busway.domain.models.DataMetadataModel
import ci.justapp.busway.domain.repositories.DataMetadataRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Implementation of the [DataMetadataRepository] interface.
 *
 * This class provides methods for interacting with the underlying data storage
 * to manage [DataMetadataModel] objects. It uses a [DataMetadataDao] to perform
 * database operations.
 *
 * @property metadataDao The Data Access Object for [DataMetadataEntity]. Used to interact with the database.
 */
class DataMetadataRepositoryImpl @Inject constructor(private val metadataDao: DataMetadataDao, private val apiService: DataMetadataApiService) :
    DataMetadataRepository {

    override fun getMetadata(): Flow<List<DataMetadataModel>> {
        return metadataDao.findMany().map { metadata -> metadata.map { it.toModel() } }
    }

    override suspend fun getMetadataById(id: String): DataMetadataModel? {
        return metadataDao.findById(id)?.toModel()
    }

    @WorkerThread
    override suspend fun insert(metadata: DataMetadataModel) {
        metadataDao.insert(metadata.toEntity())
    }

    @WorkerThread
    override suspend fun insertMany(list: List<DataMetadataModel>) {
        return metadataDao.insertMany(list.map { it.toEntity() })
    }

    @WorkerThread
    override suspend fun fetchAndStoreDataMetadataFromApi() {
        try {
            Log.d("TC_API", "Appel API des metadata de transport...")
            val response = apiService.getDataMetadata()

            Log.d("TC_API", "Insertion en base de ${response.data.size} metadata...")
            insertMany(response.data)
            Log.d("TC_API", "Synchronisation des metadata réussie.")
        } catch (e: Exception) {
            Log.e("TC_API", "Erreur lors de la synchronisation : ${e.message}", e)
        }
    }

    @WorkerThread
    override suspend fun update(metadata: DataMetadataModel) {
        return metadataDao.update(metadata.toEntity())
    }

    @WorkerThread
    override suspend fun delete(metadata: DataMetadataModel) {
        return metadataDao.delete(metadata.toEntity())
    }

    private fun DataMetadataEntity.toModel(): DataMetadataModel {
        return DataMetadataModel(
            id = id,
            lastVersion = lastVersion,
            lastUpdatedAt = lastUpdatedAt.toString(),
        )
    }

    private fun DataMetadataModel.toEntity(): DataMetadataEntity {
        return DataMetadataEntity(
            id = id,
            lastVersion = lastVersion,
            lastUpdatedAt = getLastUpdatedAtAsLong(),
        )
    }
}