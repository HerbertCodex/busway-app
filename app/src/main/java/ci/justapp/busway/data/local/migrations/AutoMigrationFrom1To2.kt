package ci.justapp.busway.data.local.migrations

import androidx.room.DeleteTable
import androidx.room.migration.AutoMigrationSpec

@DeleteTable.Entries(
    DeleteTable(tableName = "categories"),
    DeleteTable(tableName = "sotra_bus_stops")
)
class AutoMigrationFrom1To2 : AutoMigrationSpec
