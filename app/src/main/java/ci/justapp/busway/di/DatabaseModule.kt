package ci.justapp.busway.di

import android.content.Context
import ci.justapp.busway.data.local.dao.CityDao
import ci.justapp.busway.data.local.dao.CommuneDao
import ci.justapp.busway.data.local.dao.CountryDao
import ci.justapp.busway.data.local.dao.DataMetadataDao
import ci.justapp.busway.data.local.dao.TransportCompanyDao
import ci.justapp.busway.data.local.dao.TransportLineDao
import ci.justapp.busway.data.local.dao.TransportModeDao
import ci.justapp.busway.data.local.dao.TransportTypeDao
import ci.justapp.busway.data.local.db.BusWayDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * **DatabaseModule**
 *
 * This Dagger Hilt module provides dependencies related to the local database (Room).
 * It configures and provides access to the [BusWayDatabase] instance and its DAOs (Data Access Objects).
 *
 * The module is installed in the [SingletonComponent], meaning that the provided instances will
 * have a singleton scope within the application.
 *
 * **Dependencies Provided:**
 * - [BusWayDatabase]: The main database instance.
 * - [CityDao]: DAO for interacting with the 'City' entity.
 * - [CommuneDao]: DAO for interacting with the 'Commune' entity.
 * - [CountryDao]: DAO for interacting with the 'Country' entity.
 * - [DataMetadataDao]: DAO for interacting with the 'DataMetadata' entity.
 * - [TransportCompanyDao]: DAO for interacting with the 'TransportCompany' entity.
 * - [TransportLineDao]: DAO for interacting with the 'TransportLine' entity.
 * - [TransportModeDao]: DAO for interacting with the 'TransportMode' entity.
 * - [TransportTypeDao]: DAO for interacting with the 'TransportType' entity.
 *
 * **Usage:**
 * Components that need to interact with the database can inject these dependencies into their constructors.
 * Example:
 * ```
 * class MyRepository @Inject constructor(
 *     private val cityDao: CityDao,
 *     private val database: BusWayDatabase
 * ) {
 *     // ... use cityDao and database
 * }
 * ```
 *
 * **Annotations:**
 * - `@Module`: Indicates that this class is a Dagger module.
 * - `@InstallIn(SingletonComponent::class)`: Specifies that this module should be installed in the application-level component.
 * - `@Provides`: Marks a method that provides a dependency.
 * - `@Singleton`: Indicates that the provided dependency should be a singleton.
 * - `@ApplicationContext`: Provides the application's context.
 */
@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BusWayDatabase {
        return BusWayDatabase.getDatabase(context)
    }

    @Provides
    @Singleton
    fun provideCityDao(database: BusWayDatabase): CityDao = database.cityDao()

    @Provides
    @Singleton
    fun provideCommuneDao(database: BusWayDatabase): CommuneDao = database.communeDao()

    @Provides
    @Singleton
    fun provideCountryDao(database: BusWayDatabase): CountryDao = database.countryDao()

    @Provides
    @Singleton
    fun provideDataMetadataDao(database: BusWayDatabase): DataMetadataDao =
        database.dataMetadataDao()

    @Provides
    @Singleton
    fun provideTransportCompanyDao(database: BusWayDatabase): TransportCompanyDao =
        database.transportCompanyDao()

    @Provides
    @Singleton
    fun provideTransportLineDao(database: BusWayDatabase): TransportLineDao =
        database.transportLineDao()

    @Provides
    @Singleton
    fun provideTransportModeDao(database: BusWayDatabase): TransportModeDao =
        database.transportModeDao()

    @Provides
    @Singleton
    fun provideTransportTypeDao(database: BusWayDatabase): TransportTypeDao =
        database.transportTypeDao()
}
