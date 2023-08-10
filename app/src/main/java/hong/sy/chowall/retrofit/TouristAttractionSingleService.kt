package hong.sy.chowall.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface TouristAttractionSingleService {
    @GET("attraction/{id}")
    fun getTouristAttractionsingleData(@Path("id") id : Int) : Call<TouristAttractionSingleResponse>
}