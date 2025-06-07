package ci.justapp.busway.data.remote.services

import ci.justapp.busway.data.remote.response.PaginatedResponse
import ci.justapp.busway.domain.models.TransportLineModel
import ci.justapp.busway.domain.models.TransportModeModel
import retrofit2.http.GET
import retrofit2.http.Query

interface TransportLineApiService {
    @GET("transport-lines")
    suspend fun getLines(
        @Query("limit") limit: Int = 1,
        @Query("offset") offset: Int = 0
    ): PaginatedResponse<TransportLineModel>
}