package com.gundogar.rickandmorty

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface CharacterDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCharacterList(character: List<Character>)


    @Query("SELECT * FROM Character")
    suspend fun getCharacters(): List<Character>




}