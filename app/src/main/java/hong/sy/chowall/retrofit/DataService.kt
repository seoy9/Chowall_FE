package hong.sy.chowall.retrofit

import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface DataService {
    @POST("user/addUser")
    fun getRegisterResponse(@Body user : User) : Call<String>
}