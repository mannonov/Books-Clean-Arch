package com.mannonov.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mannonov.data.database.entitiy.BookDatabaseModel

@Dao
interface AppDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBook(bookDatabaseModel: BookDatabaseModel)

    @Query("SELECT * FROM books_table")
    fun getBooks(): List<BookDatabaseModel>

}