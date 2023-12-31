package com.ddwu.chowall.retrofit

data class TouristAttractionSingleResponse(
    val `data`: Data,
    val success: Boolean
) {
    data class Data(
        val address: String,
        val attractionId: Int,
        val attractionType: String,
        val breakTime: String,
        val companionRequired: Boolean,
        val hasLift: Boolean,
        val hasParking: Boolean,
        val hasRamp: Boolean,
        val hasToilet: Boolean,
        val hasWheelchair: Boolean,
        val imgId: Int,
        val name: String,
        val number: String,
        val openingHours: String,
        val url: String
    )
}