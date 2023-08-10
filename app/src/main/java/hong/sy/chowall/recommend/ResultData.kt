package hong.sy.chowall.recommend

import android.graphics.Bitmap
import android.net.Uri
import android.os.Parcel
import android.os.Parcelable
import androidx.versionedparcelable.ParcelField
import com.google.gson.annotations.SerializedName
import java.io.InputStream

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
    @SerializedName("url") val url  : String?,
    @SerializedName("imgUri") var imgUri : String = ""
) : java.io.Serializable