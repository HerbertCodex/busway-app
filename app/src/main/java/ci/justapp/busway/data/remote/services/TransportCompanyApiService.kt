package ci.justapp.busway.data.remote.services

import ci.justapp.busway.data.remote.dto.PaginatedResponse
import ci.justapp.busway.data.remote.dto.TransportCompanyDto
import retrofit2.http.GET
import retrofit2.http.Query

interface TransportCompanyApiService {
    @GET("/transport-companies") // à adapter selon ton endpoint exact
    suspend fun getTransportCompanies(
        @Query("page") page: Int = 0,
        @Query("pageSize") pageSize: Int = 100
    ): PaginatedResponse<TransportCompanyDto>
}