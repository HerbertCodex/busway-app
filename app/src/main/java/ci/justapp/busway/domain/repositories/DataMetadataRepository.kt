package ci.justapp.busway.domain.repositories

import ci.justapp.busway.domain.models.DataMetadataModel
import kotlinx.coroutines.flow.Flow

/**
 * Interface for managing the persistence and retrieval of [DataMetadataModel] objects.
 * This repository provides methods for interacting with a data source (e.g., database, network)
 * to perform CRUD (Create, Read, Update, Delete) operations on metadata entries.
 */
interface DataMetadataRepository {
    fun getMetadata(): Flow<List<DataMetadataModel>>
    suspend fun getMetadataById(id: String): DataMetadataModel?
    suspend fun insert(metadata: DataMetadataModel)
    suspend fun insertMany(list: List<DataMetadataModel>)
    suspend fun update(metadata: DataMetadataModel)
    suspend fun delete(metadata: DataMetadataModel)
}