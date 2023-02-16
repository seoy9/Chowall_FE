package hong.sy.chowall.retrofit

import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.FieldMap
import retrofit2.http.POST
import retrofit2.http.Query

interface DataService {
    @POST("user/addUser")
    fun getRegisterResponse(@Body user : JSONObject) : Call<String>
}