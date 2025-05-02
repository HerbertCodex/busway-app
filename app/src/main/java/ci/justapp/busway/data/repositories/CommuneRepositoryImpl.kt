package ci.justapp.busway.data.repositories

import androidx.annotation.WorkerThread
import ci.justapp.busway.data.local.dao.CommuneDao
import ci.justapp.busway.data.local.entities.CommuneEntity
import ci.justapp.busway.domain.models.CommuneModel
import ci.justapp.busway.domain.repositories.CommuneRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Concrete implementation of the [CommuneRepository] interface.
 *
 * This class handles the data layer operations for [CommuneModel] entities, interacting directly with the [CommuneDao].
 * It provides methods for retrieving, inserting, updating, and deleting communes from the underlying data source (e.g., a database).
 *
 * @property communeDao The Data Access Object (DAO) for [CommuneEntity], used for database interactions.
 */
class CommuneRepositoryImpl @Inject constructor(private val communeDao: CommuneDao) :
    CommuneRepository {

    override fun getCommunes(): Flow<List<CommuneModel>> {
        return communeDao.findMany().map { communes -> communes.map { it.toModel() } }
    }

    override suspend fun getCommuneBySlug(slug: String): CommuneModel? {
        return communeDao.findBySlug(slug)?.toModel()
    }

    @WorkerThread
    override suspend fun insert(commune: CommuneModel) {
        return communeDao.insert(commune.toEntity())
    }

    @WorkerThread
    override suspend fun insertMany(communes: List<CommuneModel>) {
        return communeDao.insertMany(communes.map { it.toEntity() })
    }

    @WorkerThread
    override suspend fun update(commune: CommuneModel) {
        return communeDao.update(commune.toEntity())
    }

    @WorkerThread
    override suspend fun delete(commune: CommuneModel) {
        return communeDao.delete(commune.toEntity())
    }

    private fun CommuneEntity.toModel(): CommuneModel {
        return CommuneModel(
            id = id,
            name = name,
            slug = slug,
            code = code,
            cityId = cityId,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    private fun CommuneModel.toEntity(): CommuneEntity {
        return CommuneEntity(
            id = id,
            name = name,
            slug = slug,
            code = code,
            cityId = cityId,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}