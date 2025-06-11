package ci.justapp.busway.domain.repositories

import ci.justapp.busway.domain.models.DataMetadataModel

interface DataRepository {
    suspend fun deleteAll()
}