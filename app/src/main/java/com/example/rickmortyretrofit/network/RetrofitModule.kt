package com.example.rickmortyretrofit.network

import com.example.rickmortyretrofit.BuildConfig
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitModule {
    private const val BASE_URL = "https://api.openweathermap.org/data/2.5/"
    private const val API_KEY = "0e9756ddd209232644b003efc7484626"

    private fun getLoggingInterceptor(): Interceptor {
        val level =  if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        return HttpLoggingInterceptor().setLevel(level)
    }

    private fun getKeyInterceptor(): Interceptor = Interceptor { chain ->
        val request = chain.request()
        val modifiedURL = request.url.newBuilder().addQueryParameter("appid=", API_KEY).build()
        val modifiedRequest = request.newBuilder().url(modifiedURL).build()
        chain.proceed(modifiedRequest)
    }

    private fun getOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(getKeyInterceptor())
            .addInterceptor(getLoggingInterceptor())
            .build()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .client(getOkHttpClient())
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    fun createApi(): RickAndMortyApi {
        return getRetrofit().create(RickAndMortyApi::class.java)
    }
}