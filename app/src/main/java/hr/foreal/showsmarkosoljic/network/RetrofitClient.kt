package hr.foreal.showsmarkosoljic.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object RetrofitClient {

    private var retrofit: Retrofit? = null
    private val BASE_URL: String = "https://api.infinum.academy/api/"

    val retrofitInstance: Retrofit?
        get() {
            if (retrofit == null) {
                var httpLoggingInterceptor = HttpLoggingInterceptor()
                httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                var client = OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build()

                retrofit = Retrofit.Builder()
                    .addConverterFactory(MoshiConverterFactory.create())
                    .client(client)
                    .baseUrl(BASE_URL)
                    .build()

            }
            return retrofit
        }

}