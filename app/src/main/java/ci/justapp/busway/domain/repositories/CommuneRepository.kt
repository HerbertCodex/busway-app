package ci.justapp.busway.domain.repositories

import ci.justapp.busway.domain.models.CommuneModel
import kotlinx.coroutines.flow.Flow

/**
 * Interface defining the contract for interacting with commune data.
 * This repository handles operations related to fetching, inserting, updating, and deleting
 * CommuneModel entities.
 */
interface CommuneRepository {
    fun getCommunes(): Flow<List<CommuneModel>>
    suspend fun getCommuneBySlug(slug: String): CommuneModel?
    suspend fun insert(commune: CommuneModel)
    suspend fun insertMany(communes: List<CommuneModel>)
    suspend fun update(commune: CommuneModel)
    suspend fun delete(commune: CommuneModel)
}