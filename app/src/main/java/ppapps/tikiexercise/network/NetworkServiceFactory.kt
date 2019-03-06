package ppapps.tikiexercise.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

//const val URL = "https://raw.githubusercontent.com/tikivn/android-home-test/v2/"
const val URL = "https://api.github.com/"

object NetworkServiceFactory {
    private var mRetrofit: Retrofit? = null

    init {
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(30.toLong(), TimeUnit.SECONDS)
            .connectTimeout(30.toLong(), TimeUnit.SECONDS)
            .build()

        mRetrofit = Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
            .client(okHttpClient)
            .build();
    }

    fun provideNetworkService(): NetworkService = mRetrofit?.create(NetworkService::class.java)!!
}