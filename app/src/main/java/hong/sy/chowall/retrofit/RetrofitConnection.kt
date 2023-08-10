package hong.sy.chowall.retrofit

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConnection {
    companion object {
        //http://52.78.85.62:9000/
        private const val BASE_URL = "http://13.124.235.200:8080/"
        private var INSTANCE: Retrofit? = null
        //private val gson : Gson = GsonBuilder().setLenient().create()
        private val clientBuilder = OkHttpClient.Builder()
        private val loggingInterceptor = HttpLoggingInterceptor()

        fun getInstance(): Retrofit {
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            clientBuilder.addInterceptor(loggingInterceptor)

            INSTANCE = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(clientBuilder.build())
                .build()

            return INSTANCE!!
        }
    }
}