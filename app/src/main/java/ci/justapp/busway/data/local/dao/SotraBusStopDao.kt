package ci.justapp.busway.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import ci.justapp.busway.data.local.entities.SotraBusStopEntity

/**
 * Data Access Object (DAO) for interacting with the Sotra bus stops data in the database.
 *
 * This interface provides methods for querying, inserting, updating, and deleting
 * [SotraBusStopEntity] objects from the "sotra_bus_stops" table.
 */
@Dao
interface SotraBusStopDao {
    @Query("SELECT * FROM sotra_bus_stops ORDER BY created_at DESC")
    fun getAllSotraBusStops(): PagingSource<Int, SotraBusStopEntity>

    @Query("SELECT * FROM sotra_bus_stops WHERE id = :id")
    suspend fun getSotraBusStopById(id: String): SotraBusStopEntity?

    @Insert
    suspend fun insertSotraBusStop(sotraBusStop: SotraBusStopEntity)

    @Insert
    suspend fun insertSotraBusStops(vararg sotraBusStops: SotraBusStopEntity)

    @Update
    suspend fun updateSotraBusStop(sotraBusStop: SotraBusStopEntity)

    @Update
    suspend fun updateSotraBusStops(vararg sotraBusStops: SotraBusStopEntity)

    @Delete
    suspend fun deleteSotraBusStop(sotraBusStop: SotraBusStopEntity)

    @Delete
    suspend fun deleteSotraBusStops(vararg sotraBusStops: SotraBusStopEntity)
}