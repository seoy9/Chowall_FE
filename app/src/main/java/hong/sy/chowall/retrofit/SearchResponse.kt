package hong.sy.chowall.retrofit

data class SearchResponse(
    val `data`: Data,
    val success: Boolean
) {
    data class Data(
        val content: List<Content>,
        val empty: Boolean,
        val first: Boolean,
        val last: Boolean,
        val number: Int,
        val numberOfElements: Int,
        val pageable: Pageable,
        val size: Int,
        val sort: Sort,
        val totalElements: Int,
        val totalPages: Int
    ) {
        data class Content(
            val address: String,
            val hasLift: Boolean,
            val hasParking: Boolean,
            val hasRamp: Boolean,
            val hasToilet: Boolean,
            val hasWheelchairRental: Boolean,
            val id: Int,
            val imgUrl: Int,
            val name: String,
            val requiredCompanion: Boolean
        )

        data class Pageable(
            val offset: Int,
            val pageNumber: Int,
            val pageSize: Int,
            val paged: Boolean,
            val sort: Sort,
            val unpaged: Boolean
        ) {
            data class Sort(
                val empty: Boolean,
                val sorted: Boolean,
                val unsorted: Boolean
            )
        }

        data class Sort(
            val empty: Boolean,
            val sorted: Boolean,
            val unsorted: Boolean
        )
    }
}