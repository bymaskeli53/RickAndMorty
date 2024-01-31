package com.gundogar.rickandmorty


import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "Character")
data class Character(
    @SerializedName("created")
    val created: String? ,
    @SerializedName("gender")
    val gender: String? ,
    @SerializedName("id")
    @PrimaryKey
    val id: Int? , // Primary key olacak
    @SerializedName("image")
    val image: String? ,
    @SerializedName("name")
    val name: String? ,
    @SerializedName("species")
    val species: String? ,
    @SerializedName("status")
    val status: String? ,
    @SerializedName("type")
    val type: String? ,
    @SerializedName("url")
    val url: String?
) : Parcelable