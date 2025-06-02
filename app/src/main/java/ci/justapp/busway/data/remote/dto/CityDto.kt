package ci.justapp.busway.data.remote.dto

data class CityDto(
    val id: String,
    val name: String,
    val slug: String,
    val code: String,
    val country_id: String,
    val created_at: String,
    val updated_at: String
)

