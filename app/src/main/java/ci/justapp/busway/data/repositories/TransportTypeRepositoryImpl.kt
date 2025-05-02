package ci.justapp.busway.data.repositories

import androidx.annotation.WorkerThread
import ci.justapp.busway.data.local.dao.TransportTypeDao
import ci.justapp.busway.data.local.entities.TransportTypeEntity
import ci.justapp.busway.domain.models.TransportTypeModel
import ci.justapp.busway.domain.repositories.TransportTypeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Concrete implementation of the [TransportTypeRepository] interface.
 *
 * This class handles the data layer operations related to [TransportTypeModel] and [TransportTypeEntity].
 * It interacts with the [TransportTypeDao] to perform database operations and provides
 * methods to retrieve, insert, update, and delete transport types.
 *
 * @property transportTypeDao The Data Access Object (DAO) for [TransportTypeEntity], used for
 * interacting with the database.
 */
class TransportTypeRepositoryImpl @Inject constructor(private val transportTypeDao: TransportTypeDao) :
    TransportTypeRepository {

    override fun getTransportTypes(): Flow<List<TransportTypeModel>> {
        return transportTypeDao.findMany()
            .map { transportTypes -> transportTypes.map { it.toModel() } }
    }

    override suspend fun getTransportTypeBySlug(slug: String): TransportTypeModel? {
        return transportTypeDao.findBySlug(slug)?.toModel()
    }

    @WorkerThread
    override suspend fun insert(type: TransportTypeModel) {
        return transportTypeDao.insert(type.toEntity())
    }

    @WorkerThread
    override suspend fun insertMany(types: List<TransportTypeModel>) {
        return transportTypeDao.insertMany(types.map { it.toEntity() })
    }

    @WorkerThread
    override suspend fun update(type: TransportTypeModel) {
        return transportTypeDao.update(type.toEntity())
    }

    @WorkerThread
    override suspend fun delete(type: TransportTypeModel) {
        return transportTypeDao.delete(type.toEntity())
    }

    private fun TransportTypeEntity.toModel(): TransportTypeModel {
        return TransportTypeModel(
            id = id,
            name = name,
            slug = slug,
            code = code,
            companyId = companyId,
            modeId = modeId,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }

    private fun TransportTypeModel.toEntity(): TransportTypeEntity {
        return TransportTypeEntity(
            id = id,
            name = name,
            slug = slug,
            code = code,
            companyId = companyId,
            modeId = modeId,
            createdAt = createdAt,
            updatedAt = updatedAt,
        )
    }
}