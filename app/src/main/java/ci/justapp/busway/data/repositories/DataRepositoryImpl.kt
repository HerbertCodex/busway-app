package ci.justapp.busway.data.repositories

import android.util.Log
import ci.justapp.busway.data.local.dao.*
import ci.justapp.busway.data.local.dao.TransportLineDao
import ci.justapp.busway.domain.repositories.DataRepository
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
    private val cityDao: CityDao,
    private val countryDao: CountryDao,
    private val communeDao: CommuneDao,
    private val dataMetadataDao: DataMetadataDao,
    private val transportCompanyDao: TransportCompanyDao,
    private val transportLineDao: TransportLineDao,
    private val transportModeDao: TransportModeDao,
    private val transportTypeDao: TransportTypeDao
)  : DataRepository {

    override suspend fun deleteAll() {

        Log.d("DATA_REPOSITORY", "Start deleting data...")

        transportLineDao.deleteAll()
        Log.d("DATA_REPOSITORY", "TransportLine data deleted...")

        transportTypeDao.deleteAll()
        Log.d("DATA_REPOSITORY", "TransportType data deleted...")

        communeDao.deleteAll()
        Log.d("DATA_REPOSITORY", "Commune data deleted...")

        transportModeDao.deleteAll()
        Log.d("DATA_REPOSITORY", "TransportMode data deleted...")

        cityDao.deleteAll()
        Log.d("DATA_REPOSITORY", "City data deleted...")


        transportCompanyDao.deleteAll()
        Log.d("DATA_REPOSITORY", "TransportCompany data deleted...")

        countryDao.deleteAll()
        Log.d("DATA_REPOSITORY", "Country data deleted...")


        dataMetadataDao.deleteAll()
        Log.d("DATA_REPOSITORY", "All data deleted with success!")

    }
}