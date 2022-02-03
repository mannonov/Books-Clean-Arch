package com.mannonov.data.database.entitiy

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity(tableName = "books_table")
data class BookDatabaseModel(
    @SerializedName("author")
    val author: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("genre")
    val genre: String,
    @PrimaryKey
    val id: Int,
    @SerializedName("author")
    val image: String,
    @SerializedName("isbn")
    val isbn: String,
    @SerializedName("published")
    val published: String,
    @SerializedName("publisher")
    val publisher: String,
    @SerializedName("title")
    val title: String,
) : Serializable