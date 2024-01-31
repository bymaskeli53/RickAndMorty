package com.gundogar.rickandmorty

import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CharacterRemoteDataSource @Inject constructor(
   private val characterService: CharacterService
) {


    suspend fun getAllCharacters() = characterService.getAllCharacters()
}