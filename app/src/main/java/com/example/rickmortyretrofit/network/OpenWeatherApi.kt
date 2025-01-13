package com.example.rickmortyretrofit.network
import com.example.rickmortyretrofit.model.RickAndMortyResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface OpenWeatherApi {

    @GET("weather/?")
    suspend fun getAllCharacters(): RickAndMortyResponse
}