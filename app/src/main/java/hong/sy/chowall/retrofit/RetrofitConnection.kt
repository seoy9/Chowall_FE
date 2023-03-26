package hong.sy.chowall.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConnection {
    companion object {
        //http://52.78.85.62:9000/
        private const val BASE_URL = "http://52.78.85.62:9000/"
        private var INSTANCE: Retrofit? = null
        private val gson : Gson = GsonBuilder().setLenient().create()

        fun getInstance(): Retrofit {
            INSTANCE = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
            return INSTANCE!!
        }
    }
}