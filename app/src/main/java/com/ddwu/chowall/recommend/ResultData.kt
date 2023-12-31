package com.ddwu.chowall.recommend

import com.google.gson.annotations.SerializedName

data class ResultData(
    @SerializedName("attractionId") val attractionId : Int,
    @SerializedName("name") val name : String,
    @SerializedName("address") val address : String,
    @SerializedName("number") val number : String = "",
    @SerializedName("openingHours") val openingHours : String = "",
    @SerializedName("breakTime") val breakTime : String = "",
    @SerializedName("hasRamp") val hasRamp : Boolean,
    @SerializedName("hasToilet") val hasToilet : Boolean,
    @SerializedName("hasParking") val hasParking : Boolean,
    @SerializedName("hasLift") val hasLift : Boolean,
    @SerializedName("companionRequired") val companionRequired : Boolean,
    @SerializedName("hasWheelchair") val hasWheelchair : Boolean,
    @SerializedName("attractionType") val attractionType : String,
    @SerializedName("imgId") val imgId : Int = -1,
    @SerializedName("url") val url  : String
) : java.io.Serializable