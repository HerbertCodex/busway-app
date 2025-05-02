package ci.justapp.busway.data.local.db

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ci.justapp.busway.data.local.dao.CityDao
import ci.justapp.busway.data.local.dao.CommuneDao
import ci.justapp.busway.data.local.dao.CountryDao
import ci.justapp.busway.data.local.dao.DataMetadataDao
import ci.justapp.busway.data.local.dao.TransportCompanyDao
import ci.justapp.busway.data.local.dao.TransportLineDao
import ci.justapp.busway.data.local.dao.TransportModeDao
import ci.justapp.busway.data.local.dao.TransportTypeDao
import ci.justapp.busway.data.local.entities.CityEntity
import ci.justapp.busway.data.local.entities.CommuneEntity
import ci.justapp.busway.data.local.entities.CountryEntity
import ci.justapp.busway.data.local.entities.DataMetadataEntity
import ci.justapp.busway.data.local.entities.TransportCompanyEntity
import ci.justapp.busway.data.local.entities.TransportLineEntity
import ci.justapp.busway.data.local.entities.TransportModeEntity
import ci.justapp.busway.data.local.entities.TransportTypeEntity
import ci.justapp.busway.data.local.migrations.AutoMigrationFrom1To2


/**
 * [BusWayDatabase] is the Room database class for the BusWay application.
 *
 * It defines the database schema, including the entities and their relationships,
 * and provides access to the Data Access Objects (DAOs) for each entity.
 *
 * This database stores information related to cities, communes, countries,
 * data metadata, transport companies, transport lines, transport modes, and transport types.
 *
 * @property cityDao Provides access to the [CityDao] for interacting with [CityEntity] data.
 * @property countryDao Provides access to the [CountryDao] for interacting with [CountryEntity] data.
 * @property communeDao Provides access to the [CommuneDao] for interacting with [CommuneEntity] data.
 * @property dataMetadataDao Provides access to the [DataMetadataDao] for interacting with [DataMetadataEntity] data.
 */
@Database(
    entities = [
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
