package hong.sy.chowall.retrofit

import retrofit2.Call
import retrofit2.http.*

interface DataService {
    @POST("user/addUser")
    fun getAddUser(@Body user : User) : Call<String>

    @POST("user/getId")
    fun getCheckId(@Body user : User) : Call<String>
}