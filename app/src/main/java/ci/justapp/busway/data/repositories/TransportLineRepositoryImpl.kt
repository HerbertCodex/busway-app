package ci.justapp.busway.data.repositories

import androidx.annotation.WorkerThread
import ci.justapp.busway.data.local.dao.TransportLineDao
import ci.justapp.busway.data.local.entities.TransportLineEntity
import ci.justapp.busway.domain.models.TransportLineModel
import ci.justapp.busway.domain.repositories.TransportLineRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

/**
 * Concrete implementation of the [TransportLineRepository] interface.
 *
 * This class handles data access and manipulation for transport lines, interacting directly with
 * the [TransportLineDao] to perform database operations. It also handles the conversion between
 * [TransportLineModel] (domain) and [TransportLineEntity] (database) representations.
 *
 * @property transportLineDao The Data Access Object for transport lines, used to interact with the
 * database. Injected via constructor injection.
 */
class TransportLineRepositoryImpl @Inject constructor(private val transportLineDao: TransportLineDao) :
    TransportLineRepository {
    override fun getTransportLines(): Flow<List<TransportLineModel>> {
        return transportLineDao.findMany()
            .map { transportLines -> transportLines.map { it.toModel() } }
    }

    override suspend fun getTransportLineBySlug(slug: String): TransportLineModel? {
        return transportLineDao.findBySlug(slug)?.toModel()
    }

    @WorkerThread
    override suspend fun insert(line: TransportLineModel) {
        transportLineDao.insert(line.toEntity())
    }

    @WorkerThread
    override suspend fun insertMany(lines: List<TransportLineModel>) {
        transportLineDao.insertMany(lines.map { it.toEntity() })
    }

    @WorkerThread
    override suspend fun update(line: TransportLineModel) {
        transportLineDao.update(line.toEntity())
    }

    @WorkerThread
    override suspend fun delete(line: TransportLineModel) {
        transportLineDao.delete(line.toEntity())
    }

    private fun TransportLineEntity.toModel(): TransportLineModel {
        return TransportLineModel(
            id = id,
            slug = slug,
            line = line,
            lineNumber = lineNumber,
            openingHours = openingHours,
            companyId = companyId,
            typeTransportId = typeTransportId,
            cityId = cityId,
            startCommuneId = startCommuneId,
            endCommuneId = endCommuneId,
            geometry = geometry,
            dataVersion = dataVersion,
            syncedAt = syncedAt,
            metadataId = metadataId,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }

    private fun TransportLineModel.toEntity(): TransportLineEntity {
        return TransportLineEntity(
            id = id,
            slug = slug,
            line = line,
            lineNumber = lineNumber,
            openingHours = openingHours,
            companyId = companyId,
            typeTransportId = typeTransportId,
            cityId = cityId,
            startCommuneId = startCommuneId,
            endCommuneId = endCommuneId,
            geometry = geometry,
            dataVersion = dataVersion,
            syncedAt = syncedAt,
            metadataId = metadataId,
            createdAt = createdAt,
            updatedAt = updatedAt
        )
    }
}