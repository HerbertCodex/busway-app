package ci.justapp.busway.domain.models

/**
 * Represents a transport line with its associated details.
 *
 * This data class holds information about a specific transport line, including its
 * unique identifiers, operational details, geographical information, and metadata.
 *
 * @property id The unique identifier of the transport line.
 * @property slug A URL-friendly identifier for the transport line.
 * @property line The name or description of the transport line.
 * @property lineNumber The line number associated with the transport line.
 * @property openingHours The operating hours of the transport line.
 * @property companyId The identifier of the company operating the transport line.
 * @property typeTransportId The identifier of the transport type (e.g., bus, train, tram).
 * @property cityId The identifier of the city where the transport line operates.
 * @property startCommuneId The identifier of the commune where the line starts.
 * @property endCommuneId The identifier of the commune where the line ends.
 * @property geometry A string representing the geographical geometry of the transport line (e.g., GeoJSON).
 * @property dataVersion The version number of the data for this transport line.
 * @property syncedAt The timestamp indicating when the data was last synchronized.
 * @property metadataId The identifier of the metadata associated with this transport line.
 * @property createdAt The timestamp indicating when the transport line data was created.
 * @property updatedAt The timestamp indicating when the transport line data was last updated.
 */
data class TransportLineModel(
    val id: String,
    val slug: String,
    val line: String,
    val lineNumber: String,
    val openingHours: String,
    val companyId: String,
    val typeTransportId: String,
    val cityId: String,
    val startCommuneId: String,
    val endCommuneId: String,
    val geometry: String,
    val dataVersion: Int,
    val syncedAt: Long,
    val metadataId: String,
    val createdAt: Long,
    val updatedAt: Long
)
