package ci.justapp.busway.domain.repositories

import ci.justapp.busway.domain.models.TransportModeModel
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing TransportMode entities.
 *
 * This interface provides methods for retrieving, inserting, updating, and deleting
 * TransportModeModel objects, typically representing different modes of transportation.
 */
interface TransportModeRepository {
    fun getTransportModes(): Flow<List<TransportModeModel>>
    suspend fun getTransportModeBySlug(slug: String): TransportModeModel?
    suspend fun insert(mode: TransportModeModel)
    suspend fun insertMany(modes: List<TransportModeModel>)
    suspend fun update(mode: TransportModeModel)
    suspend fun delete(mode: TransportModeModel)
}