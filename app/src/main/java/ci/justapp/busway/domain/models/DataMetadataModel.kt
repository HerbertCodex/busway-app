package ci.justapp.busway.domain.models

/**
 * Represents metadata associated with a data entity.
 *
 * This class encapsulates information about the data's unique identifier,
 * its most recent version, and the timestamp of its last update.
 *
 * @property id The unique identifier of the data entity. This serves as the primary key.
 * @property lastVersion The most recent version number of the data entity. This can be used
 *                       to track changes and determine the freshness of the data.
 * @property lastUpdatedAt The timestamp (in milliseconds since the epoch) representing when
 *                          the data entity was last updated. This allows tracking the recency
 *                          of the data.
 */
data class DataMetadataModel(
    val id: String,
    val lastVersion: Int,
    val lastUpdatedAt: Long
)
