package hong.sy.chowall.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecommendService {
    @GET("recommend/course")
    fun getRecommendList(@Query("city") city : String, @Query("companion") companion : Int,
                         @Query("days") days : Int, @Query("type") type : Int) : Call<RecommendResponse>
}