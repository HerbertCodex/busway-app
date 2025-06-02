package ci.justapp.busway.data.remote.services

import ci.justapp.busway.data.remote.dto.CommuneDto
import ci.justapp.busway.data.remote.dto.PaginatedResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CommunesApiService {
    @GET("/communes")
    suspend fun getCommunes(
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 100
    ): PaginatedResponse<CommuneDto>
}