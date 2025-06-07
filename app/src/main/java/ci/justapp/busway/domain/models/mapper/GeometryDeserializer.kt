package ci.justapp.busway.domain.models.mapper

import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

/**
 * Custom deserializer for geometry field that converts JSON object to string
 */
class GeometryDeserializer : JsonDeserializer<String> {
    override fun deserialize(
        json: JsonElement?,
        typeOfT: Type?,
        context: JsonDeserializationContext?
    ): String {
        return when {
            json == null || json.isJsonNull -> ""
            json.isJsonPrimitive && json.asJsonPrimitive.isString -> json.asString
            else -> json.toString() // Converts JSON object to string
        }
    }
}