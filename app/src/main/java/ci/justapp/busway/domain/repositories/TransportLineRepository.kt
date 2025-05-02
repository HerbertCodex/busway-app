package ci.justapp.busway.domain.repositories

import ci.justapp.busway.domain.models.TransportLineModel
import kotlinx.coroutines.flow.Flow

/**
 * Interface for managing transport line data.
 *
 * This repository provides methods to retrieve, insert, update, and delete transport line information.
 * It utilizes Flows for reactive data streams and suspend functions for asynchronous operations.
 */
interface TransportLineRepository {
    fun getTransportLines(): Flow<List<TransportLineModel>>
    suspend fun getTransportLineBySlug(slug: String): TransportLineModel?
    suspend fun insert(line: TransportLineModel)
    suspend fun insertMany(lines: List<TransportLineModel>)
    suspend fun update(line: TransportLineModel)
    suspend fun delete(line: TransportLineModel)
}