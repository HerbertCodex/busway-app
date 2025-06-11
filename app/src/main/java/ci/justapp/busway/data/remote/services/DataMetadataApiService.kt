package ci.justapp.busway.data.remote.services

import ci.justapp.busway.data.remote.response.PaginatedResponse
import ci.justapp.busway.domain.models.DataMetadataModel
import retrofit2.http.GET
import retrofit2.http.Query

interface DataMetadataApiService {
    @GET("/metadata")
    suspend fun getDataMetadata(
        @Query("page") page: Int = 0,
        @Query("pageSize") pageSize: Int = 100
    ): PaginatedResponse<DataMetadataModel>
}