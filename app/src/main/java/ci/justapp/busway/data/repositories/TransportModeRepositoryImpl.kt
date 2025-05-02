package ci.justapp.busway.data.repositories

import androidx.annotation.WorkerThread
import ci.justapp.busway.data.local.dao.TransportModeDao
import ci.justapp.busway.data.local.entities.TransportModeEntity
import ci.justapp.busway.domain.models.TransportModeModel
import ci.justapp.busway.domain.repositories.TransportModeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Concrete implementation of the [TransportModeRepository] interface.
 *
 * This class handles the data layer operations related to [TransportModeModel]
 * entities, interacting with the [TransportModeDao] to perform CRUD operations
 * on the underlying data source.
 *
 * @property transportModeDao The Data Access Object (DAO) used to interact with the
 *                           persistence layer for transport mode entities.
 */
class TransportModeRepositoryImpl @Inject constructor(private val transportModeDao: TransportModeDao) :
    TransportModeRepository {

    override fun getTransportModes(): Flow<List<TransportModeModel>> {
        return transportModeDao.findMany()
            .map { transportModes -> transportModes.map { it.toModel() } }
    }

    override suspend fun getTransportModeBySlug(slug: String): TransportModeModel? {
        return transportModeDao.findBySlug(slug)?.toModel()
    }

    @WorkerThread
    override suspend fun insert(mode: TransportModeModel) {
        return transportModeDao.insert(mode.toEntity())
    }

    @WorkerThread
    override suspend fun insertMany(modes: List<TransportModeModel>) {
        return transportModeDao.insertMany(modes.map { it.toEntity() })
    }

    @WorkerThread
    override suspend fun update(mode: TransportModeModel) {
        return transportModeDao.update(mode.toEntity())
    }

    @WorkerThread
    override suspend fun delete(mode: TransportModeModel) {
        return transportModeDao.delete(mode.toEntity())
    }

    private fun TransportModeEntity.toModel(): TransportModeModel {
        return TransportModeModel(
            id = id,
            name = name,
            slug = slug,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    private fun TransportModeModel.toEntity(): TransportModeEntity {
        return TransportModeEntity(
            id = id,
            name = name,
            slug = slug,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}