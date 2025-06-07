package ci.justapp.busway.di

import ci.justapp.busway.data.remote.services.CityApiService
import ci.justapp.busway.data.remote.services.CommunesApiService
import ci.justapp.busway.data.remote.services.CountriesApiService
import ci.justapp.busway.data.remote.services.TransportCompanyApiService
import ci.justapp.busway.data.remote.services.TransportLineApiService
import ci.justapp.busway.data.remote.services.TransportModeApiService
import ci.justapp.busway.data.remote.services.TransportTypeApiService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
        .baseUrl("http://192.168.254.250:4000/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    @Provides
    fun provideCityApiService(retrofit: Retrofit): CityApiService =
        retrofit.create(CityApiService::class.java)

    @Provides
    fun provideCountryApiService(retrofit: Retrofit): CountriesApiService =
        retrofit.create(CountriesApiService::class.java)

    @Provides
    fun provideCommuneApiService(retrofit: Retrofit): CommunesApiService =
        retrofit.create(CommunesApiService::class.java)

    @Provides
    fun provideTransportCompanyApiService(retrofit: Retrofit): TransportCompanyApiService =
        retrofit.create(TransportCompanyApiService::class.java)

    @Provides
    fun provideTransportTypeCompanyApiService(retrofit: Retrofit): TransportTypeApiService =
        retrofit.create(TransportTypeApiService::class.java)

    @Provides
    fun provideTransportLineApiService(retrofit: Retrofit): TransportLineApiService =
        retrofit.create(TransportLineApiService::class.java)

    @Provides
    fun provideTransportModeApiService(retrofit: Retrofit): TransportModeApiService =
        retrofit.create(TransportModeApiService::class.java)

}