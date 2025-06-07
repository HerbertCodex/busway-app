package ci.justapp.busway.data.remote.services

import ci.justapp.busway.data.remote.response.PaginatedResponse
import ci.justapp.busway.domain.models.TransportCompanyModel
import retrofit2.http.GET
import retrofit2.http.Query

interface TransportCompanyApiService {
    @GET("/transport-companies")
    suspend fun getTransportCompanies(
        @Query("page") page: Int = 0,
        @Query("pageSize") pageSize: Int = 100
    ): PaginatedResponse<TransportCompanyModel>
}