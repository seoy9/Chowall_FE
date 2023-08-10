package hong.sy.chowall.retrofit

import retrofit2.Call
import retrofit2.http.*

interface SearchService {
    @Headers("Content-Type: application/json")
    @GET("search/result")
    fun getSearchList(@Query("page") page : Int, @Query("size") size : Int,
                      @Query("searchBy") searchBy : String, @Query("searchQuery") searchQuery: String) : Call<SearchResponse>
}