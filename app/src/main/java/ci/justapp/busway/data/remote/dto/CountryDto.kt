package ci.justapp.busway.data.remote.dto

data class CountryDto(
    val id: String,
    val name: String,
    val slug: String,
    val code_iso: String,  // ← Correspond au JSON
    val created_at: String,
    val updated_at: String
)
