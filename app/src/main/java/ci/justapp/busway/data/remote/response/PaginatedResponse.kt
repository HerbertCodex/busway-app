package ci.justapp.busway.data.remote.response

data class PaginatedResponse<T>(
    val hasNext: Boolean,
    val currentPage: Int,
    val totalPages: Int,
    val offset: Int,
    val nextOffset: Int?,
    val pageSize: Int,
    val previousOffset: Int?,
    val data: List<T>,
    val limit: Int,
    val total: Int,
    val hasPrevious: Boolean
)
