package ci.justapp.busway.domain.repositories

import ci.justapp.busway.domain.models.TransportTypeModel
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing TransportType data.
 * This interface defines the operations that can be performed on TransportTypeModel objects,
 * such as retrieving, inserting, updating, and deleting them.
 */
interface TransportTypeRepository {
    fun getTransportTypes(): Flow<List<TransportTypeModel>>
    suspend fun getTransportTypeBySlug(slug: String): TransportTypeModel?
    suspend fun insert(type: TransportTypeModel)
    suspend fun insertMany(types: List<TransportTypeModel>)
    suspend fun update(type: TransportTypeModel)
    suspend fun delete(type: TransportTypeModel)
}