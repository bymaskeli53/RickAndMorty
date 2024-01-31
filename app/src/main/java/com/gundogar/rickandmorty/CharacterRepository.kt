package com.gundogar.rickandmorty

import javax.inject.Inject

class CharacterRepository @Inject constructor(private val remoteDataSource: CharacterRemoteDataSource,
    private val characterDao: CharacterDao) {


    suspend fun getCharactersFromLocal() : List<Character> = characterDao.getCharacters()

    suspend fun insertCharactersToLocal(character: List<Character>)  = characterDao.insertCharacterList(character)

    suspend fun getAllCharacters() : CharacterList = remoteDataSource.getAllCharacters()
}