package ci.justapp.busway.data.local.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

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