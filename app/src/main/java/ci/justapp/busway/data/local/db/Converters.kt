package ci.justapp.busway.data.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

/**
 * Converters class provides methods for converting between List<Double> and String
 * for use with Room database's type converters.
 *
 * Room doesn't inherently know how to store complex types like List<Double> directly in a database column.
 * Type converters are used to bridge this gap, converting the complex type into a format Room understands (String, Int, etc.) and back.
 */
class Converters {
    @TypeConverter
    fun fromCoordinatesList(value: List<Double>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun toCoordinatesList(value: String): List<Double> {
        val listType = object : TypeToken<List<Double>>() {}.type
        return Gson().fromJson(value, listType)
    }
}