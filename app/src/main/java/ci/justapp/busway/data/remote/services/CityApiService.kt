package ci.justapp.busway.data.remote.services

import ci.justapp.busway.data.remote.response.PaginatedResponse
import ci.justapp.busway.domain.models.CityModel
import retrofit2.http.GET
import retrofit2.http.Query

interface CityApiService {
    @GET("cities")
    suspend fun getCities(
        @Query("page") page: Int = 1,
        @Query("pageSize") pageSize: Int = 100
    ): PaginatedResponse<CityModel>
}