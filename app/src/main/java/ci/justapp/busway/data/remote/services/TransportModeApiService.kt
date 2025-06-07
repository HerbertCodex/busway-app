package ci.justapp.busway.data.remote.services

import ci.justapp.busway.data.remote.response.PaginatedResponse
import ci.justapp.busway.domain.models.TransportModeModel
import retrofit2.http.GET
import retrofit2.http.Query

interface TransportModeApiService {
    @GET("/modes")
    suspend fun getTransportModes(
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0
    ): PaginatedResponse<TransportModeModel>
}