package com.gundogar.rickandmorty

import retrofit2.Response
import retrofit2.http.GET

interface CharacterService {

    @GET("character")
    suspend fun getAllCharacters(): CharacterList


}