package hong.sy.chowall.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConnection {
    companion object {
        //http://52.78.85.62:9000/
        private const val BASE_URL = "http://52.78.85.62:8080/"
        private var INSTANCE: Retrofit? = null

        fun getInstance(): Retrofit {
            INSTANCE = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return INSTANCE!!
        }
    }
}