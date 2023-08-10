package hong.sy.chowall.list

import android.graphics.Bitmap

data class ListData(
    val attractionId : Int,
    val name : String,
    val address : String,
    val number : String = "",
    val openingHours : String = "",
    val breakTime : String = "",
    val hasRamp : Boolean,
    val hasToilet : Boolean,
    val hasParking : Boolean,
    val hasLift : Boolean,
    val companionRequired : Boolean,
    val hasWheelchair : Boolean,
    val attractionType : String,
    val imgId : Int = -1,
    val url  : String?,
    var imgBitmap: Bitmap? = null
)
