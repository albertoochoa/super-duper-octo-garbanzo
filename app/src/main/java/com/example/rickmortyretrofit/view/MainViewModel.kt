package com.example.rickmortyretrofit.view

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rickmortyretrofit.model.Character
import com.example.rickmortyretrofit.repository.CharacterRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
) : ViewModel() {
    private val repo: CharacterRepository = CharacterRepository()

    private val _charactersFlow: MutableStateFlow<List<Character>> = MutableStateFlow(listOf())
    val charactersFlow: StateFlow<List<Character>> = _charactersFlow

    fun getAllCharacters() {
        viewModelScope.launch {
            val chars = repo.getAllCharacters()
            _charactersFlow.emit(chars)
        }
    }

    fun getCharacter() {
        viewModelScope.launch {
            val char = repo.getCharacter(777)
            _charactersFlow.emit(listOf(char))
        }
    }

    fun getSomeCharacters() {
        viewModelScope.launch {
            val chars = repo.getCharacters(777, 1, 123, 432, 124, 54, 1)
            _charactersFlow.emit(chars)
        }
    }
}