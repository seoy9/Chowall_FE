package com.ddwu.chowall.retrofit

data class RecommendResponse(
    val `data`: Data,
    val success: Boolean
) {
    data class Data(
        val courseId: String,
        val touristAttractionDtos: List<Any>
    )
}