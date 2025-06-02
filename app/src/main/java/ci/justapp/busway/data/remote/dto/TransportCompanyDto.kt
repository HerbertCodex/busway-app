package ci.justapp.busway.data.remote.dto

import com.google.gson.annotations.SerializedName

data class TransportCompanyDto(
    val id: String,
    val name: String,
    val slug: String,
    val code: String,
    val country_id: String,
    val created_at: String,
    val updated_at: String
)
