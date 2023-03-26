package hong.sy.chowall.retrofit

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("id") var id: String? = null,
    @SerializedName("name") var name: String? = null,
    @SerializedName("password") var password: String? = null,
    @SerializedName("email") var email: String? = null
)
