package ci.justapp.busway.data.remote.services

import ci.justapp.busway.data.remote.response.PaginatedResponse
import ci.justapp.busway.domain.models.CommuneModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CommunesApiService {
    @GET("/communes")
    suspend fun getCommunes(
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 100
    ): PaginatedResponse<CommuneModel>
}