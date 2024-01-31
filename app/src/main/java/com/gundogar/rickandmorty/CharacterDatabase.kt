package com.gundogar.rickandmorty

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Character::class] , version = 1)
abstract class CharacterDatabase : RoomDatabase() {

    abstract fun getDao(): CharacterDao

}