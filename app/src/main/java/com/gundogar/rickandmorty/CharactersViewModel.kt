package com.gundogar.rickandmorty

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class CharactersViewModel @Inject constructor(private val repository: CharacterRepository) :
    ViewModel() {
    private var _characters = MutableLiveData<Resource<CharacterList>>()
    val characters: LiveData<Resource<CharacterList>> get() = _characters

    var cachedData = emptyList<Character>()


    init {
        getAllCharacters()
    }

    fun getAllCharacters() {
        _characters.value = Resource.Loading()
        viewModelScope.launch {
            try {
                val data = repository.getAllCharacters()

                repository.insertCharactersToLocal(data.results)

                _characters.value = Resource.Success(data)
            } catch (e: Exception) {
                 cachedData = repository.getCharactersFromLocal()




                _characters.value = Resource.Error("You see cached data" , null)
            }

        }
        // TODO: Swipe refresh layout eklenecek
    }
}