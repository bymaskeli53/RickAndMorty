package com.gundogar.rickandmorty

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import org.w3c.dom.CharacterData
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://rickandmortyapi.com/api/")
            .build()
    }




    @Singleton
    @Provides
    fun provideCharacterService(retrofit: Retrofit): CharacterService = retrofit.create(CharacterService::class.java)


    @Singleton
    @Provides
    fun provideCharacterRemoteDataSource(service: CharacterService) : CharacterRemoteDataSource = CharacterRemoteDataSource(service)


    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): CharacterDatabase {
       return Room.databaseBuilder(context = context, klass = CharacterDatabase::class.java,"character_database").build()

    }

    @Singleton
    @Provides
    fun provideDao(characterDatabase: CharacterDatabase) : CharacterDao {
        return characterDatabase.getDao()
    }
}