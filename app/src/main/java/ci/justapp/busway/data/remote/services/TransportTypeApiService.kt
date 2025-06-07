package ci.justapp.busway.data.remote.services

import ci.justapp.busway.data.remote.response.PaginatedResponse
import ci.justapp.busway.domain.models.TransportTypeModel
import retrofit2.http.GET
import retrofit2.http.Query

interface TransportTypeApiService {
    @GET("/transport-types")
    suspend fun getTransportTypes(
        @Query("limit") limit: Int = 100,
        @Query("offset") offset: Int = 0
    ): PaginatedResponse<TransportTypeModel>
}