package ci.justapp.busway.data.remote.dto

data class CommuneDto(
    val id: String,
    val name: String,
    val slug: String,
    val code: String,
    val city_id: String,
    val created_at: String,
    val updated_at: String
)
