package ci.justapp.busway.di

import ci.justapp.busway.data.repositories.CityRepositoryImpl
import ci.justapp.busway.data.repositories.CommuneRepositoryImpl
import ci.justapp.busway.data.repositories.CountryRepositoryImpl
import ci.justapp.busway.data.repositories.DataMetadataRepositoryImpl
import ci.justapp.busway.data.repositories.TransportCompanyRepositoryImpl
import ci.justapp.busway.data.repositories.TransportLineRepositoryImpl
import ci.justapp.busway.data.repositories.TransportModeRepositoryImpl
import ci.justapp.busway.data.repositories.TransportTypeRepositoryImpl
import ci.justapp.busway.domain.repositories.CityRepository
import ci.justapp.busway.domain.repositories.CommuneRepository
import ci.justapp.busway.domain.repositories.CountryRepository
import ci.justapp.busway.domain.repositories.DataMetadataRepository
import ci.justapp.busway.domain.repositories.TransportCompanyRepository
import ci.justapp.busway.domain.repositories.TransportLineRepository
import ci.justapp.busway.domain.repositories.TransportModeRepository
import ci.justapp.busway.domain.repositories.TransportTypeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * ## BusWayModule
 *
 * This Dagger Hilt module provides bindings for various repository interfaces within the BusWay application.
 * It ensures that each repository is bound to its respective implementation and is available as a singleton
 * throughout the application's lifecycle.
 *
 * **Key Features:**
 *
 * *   **Dependency Injection:** Utilizes Dagger Hilt for dependency injection.
 * *   **Singleton Scope:**  All repository bindings are scoped as `@Singleton`, meaning only one instance
 *      of each repository implementation will be created and shared across the application.
 * *   **Repository Bindings:** Provides bindings for the following repository interfaces:
 *     *   `CityRepository`
 *     *   `CommuneRepository`
 *     *   `CountryRepository`
 *     *   `DataMetadataRepository`
 *     *   `TransportCompanyRepository`
 *     *   `TransportLineRepository`
 *     *   `TransportModeRepository`
 *     *   `TransportTypeRepository`
 * *   **Abstraction:** Binds interfaces to their concrete implementations, allowing for
 *      easy swapping of implementations if needed.
 * *   **Centralized Configuration:**  Defines all repository bindings in a single, organized location.
 * * **Hilt Integration:** Installed in `SingletonComponent` to be available during the whole app lifecycle.
 *
 * **Usage:**
 *
 * When a class in the application needs to use one of these repositories, it can simply request the interface
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class BusWayModule {

    @Binds
    @Singleton
    abstract fun bindCityRepository(impl: CityRepositoryImpl): CityRepository

    @Binds
    @Singleton
    abstract fun bindCommuneRepository(impl: CommuneRepositoryImpl): CommuneRepository

    @Binds
    @Singleton
    abstract fun bindCountryRepository(impl: CountryRepositoryImpl): CountryRepository

    @Binds
    @Singleton
    abstract fun bindDataMetadataRepository(impl: DataMetadataRepositoryImpl): DataMetadataRepository

    @Binds
    @Singleton
    abstract fun bindTransportCompanyRepository(impl: TransportCompanyRepositoryImpl): TransportCompanyRepository

    @Binds
    @Singleton
    abstract fun bindTransportLineRepository(impl: TransportLineRepositoryImpl): TransportLineRepository

    @Binds
    @Singleton
    abstract fun bindTransportModeRepository(impl: TransportModeRepositoryImpl): TransportModeRepository

    @Binds
    @Singleton
    abstract fun bindTransportTypeRepository(impl: TransportTypeRepositoryImpl): TransportTypeRepository
}