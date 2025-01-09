package com.example.rickmortyretrofit.repository

import com.example.rickmortyretrofit.model.Character
import com.example.rickmortyretrofit.network.RetrofitModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

interface CharacterRepositoryContract {
    suspend fun getAllCharacters(): List<Character>
    suspend fun getCharacter(id: Int): Character?
    suspend fun getCharacters(vararg id: Int): List<Character>
}


class DemoCharacterRepository : CharacterRepositoryContract {


    override suspend fun getAllCharacters(): List<Character> {
        return withContext(Dispatchers.IO) {
            delay(2000)
            return@withContext listOf(
//                Character("rick", "Human", Planet("Earth C-137", "google.com")),
//                Character("rick", "Human", Planet("Earth C-137", "google.com")),
            )
        }
    }

    override suspend fun getCharacter(id: Int): Character? {
        return null
    }

    override suspend fun getCharacters(vararg id: Int): List<Character> {
        return emptyList()
    }
}

class CharacterRepository : CharacterRepositoryContract {
    private val api = RetrofitModule.createApi()

    override suspend fun getAllCharacters(): List<Character> {
        return withContext(Dispatchers.IO) {
            delay(timeMillis = 5000)
            return@withContext api.getAllCharacters().results
        }
    }

    override suspend fun getCharacter(id: Int): Character {
        return withContext(Dispatchers.IO) {
            delay(timeMillis = 7000)
            return@withContext api.getSingleCharacter(id.toString())
        }
    }

    override suspend fun getCharacters(vararg ids: Int): List<Character> {
        val string = StringBuilder("${ids.first()}")
        for (id in 1..ids.lastIndex) {
            string.append(",")
            string.append(id)
        }
        return withContext(Dispatchers.IO) {
            delay(timeMillis = 2000)
            return@withContext api.getSomeCharacters(string.toString())
        }
    }
}

