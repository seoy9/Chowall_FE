package hong.sy.chowall.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitConnection {
    companion object {
        private const val BASE_URL = "http://13.124.235.200:8080/"
        private var INSTANCE: Retrofit? = null
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