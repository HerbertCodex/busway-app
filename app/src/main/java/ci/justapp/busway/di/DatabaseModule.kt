package ci.justapp.busway.di

import android.content.Context
import ci.justapp.busway.data.local.dao.CategoryDao
import ci.justapp.busway.data.local.dao.SotraBusStopDao
import ci.justapp.busway.data.local.db.BusWayDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): BusWayDatabase {
        return BusWayDatabase.getDatabase(context)
    }

    @Provides
    fun provideCategoryDao(database: BusWayDatabase): CategoryDao {
        return database.categoryDao()
    }

    @Provides
    fun provideSotraBusStopDao(database: BusWayDatabase): SotraBusStopDao {
        return database.sotraBusStopDao()
    }
}