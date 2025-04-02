package ci.justapp.busway.data.local.db

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ci.justapp.busway.data.local.dao.*
import ci.justapp.busway.data.local.entities.*
import ci.justapp.busway.data.local.migrations.AutoMigrationFrom1To2

/**
 * [BusWayDatabase] is the Room database for the BusWay application.
 *
 * Entities:
 * - Core transport data (cities, communes, countries, companies, modes, etc.)
 * - Transport structures (lines, types)
 * - System metadata
 *
 * Version:
 * - 2: The current version of the database.
 *
 * Auto-migrations:
 * - From version 1 to 2: Automatically handles migration.
 */
@Database(
    entities = [
        // Transport structure
        CityEntity::class,
        CommuneEntity::class,
        CountryEntity::class,
        DataMetadataEntity::class,
        TransportCompanyEntity::class,
        TransportLineEntity::class,
        TransportModeEntity::class,
        TransportTypeEntity::class
    ],
    version = 2,
    autoMigrations = [
        AutoMigration(from = 1, to = 2, spec = AutoMigrationFrom1To2::class)
    ],
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class BusWayDatabase : RoomDatabase() {

    abstract fun cityDao(): CityDao
    abstract fun countryDao(): CountryDao
    abstract fun communeDao(): CommuneDao
    abstract fun dataMetadataDao(): DataMetadataDao
    abstract fun transportCompanyDao(): TransportCompanyDao
    abstract fun transportLineDao(): TransportLineDao
    abstract fun transportModeDao(): TransportModeDao
    abstract fun transportTypeDao(): TransportTypeDao


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
