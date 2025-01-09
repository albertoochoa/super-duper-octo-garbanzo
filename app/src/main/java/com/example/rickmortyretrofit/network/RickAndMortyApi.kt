package com.example.rickmortyretrofit.network

import com.example.rickmortyretrofit.model.Character
import com.example.rickmortyretrofit.model.RickAndMortyResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface RickAndMortyApi {
    @GET("character")
    suspend fun getAllCharacters(): RickAndMortyResponse

    @GET("character/{id}")
    suspend fun getSingleCharacter(@Path("id") characterId: String): Character

    @GET("character/{ids}")
    suspend fun getSomeCharacters(@Path("ids") characterId: String): List<Character>
}