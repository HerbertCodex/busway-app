package ci.justapp.busway.data.local.db

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ci.justapp.busway.data.local.dao.CategoryDao
import ci.justapp.busway.data.local.dao.SotraBusStopDao
import ci.justapp.busway.data.local.entities.CategoryEntity
import ci.justapp.busway.data.local.entities.SotraBusStopEntity

/**
 * [BusWayDatabase] is the Room database for the BusWay application.
 *
 * This class defines the database configuration, including the entities, version,
 * auto-migrations, type converters, and DAO access methods. It also provides a
 * singleton instance of the database.
 *
 * Entities:
 * - [SotraBusStopEntity]: Represents a bus stop in the database.
 * - [CategoryEntity]: Represents a category for bus stops or other data.
 *
 * Version:
 * - 2: The current version of the database.
 *
 * Auto-migrations:
 * - From version 1 to 2: Automatically handles the migration process between these versions.
 *
 * Type Converters:
 * - [Converters]: Provides type conversion functionality for data not directly supported by Room.
 *
 * DAOs:
 * - [SotraBusStopDao]: Data access object for interacting with [SotraBusStopEntity].
 * - [CategoryDao]: Data access object for interacting with [CategoryEntity].
 */
@Database(
    entities = [SotraBusStopEntity::class, CategoryEntity::class],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ],
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class BusWayDatabase : RoomDatabase() {
    abstract fun sotraBusStopDao(): SotraBusStopDao
    abstract fun categoryDao(): CategoryDao

    companion object {

        @Volatile
        private var INSTANCE: BusWayDatabase? = null
        private const val DATABASE_NAME = "busway_db"

        fun getDatabase(context: Context): BusWayDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    BusWayDatabase::class.java,
                    DATABASE_NAME
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}