package com.mannonov.myapplication.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mannonov.domains.interactor.BookInteractor
import com.mannonov.domains.models.Book
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class BookViewModel @Inject constructor(private val bookInteractor: BookInteractor) : ViewModel() {

    private var booksDatabase: MutableLiveData<List<Book>> = MutableLiveData()

    init {
        getBooksFromDatabase()
    }

    fun getBooks(count: Int): StateFlow<BookResource> {
        val bookStateFlow = MutableStateFlow<BookResource>(BookResource.Loading)
        viewModelScope.launch {
            bookInteractor.getBooks(count)
                .catch {
                    bookStateFlow.emit(BookResource.Error(it.message ?: "error"))
                }
                .collect {
                    if (it.isSuccess) {
                        Log.d("BookResponse", "getBooks: ${it.getOrNull()!!.total}")
                        bookStateFlow.emit(BookResource.Success(it.getOrNull()!!.data))
                    } else if (it.isFailure) {
                        bookStateFlow.emit(BookResource.Error(it.exceptionOrNull()?.message))
                    }
                }
        }
        return bookStateFlow
    }

    private fun getBooksFromDatabase() {
        viewModelScope.launch {
            withContext(Dispatchers.Main) {
                booksDatabase.value = bookInteractor.getBooksFromDatabase()
            }
        }
    }

    fun insertBookToDatabase(book: Book) {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                bookInteractor.insertBookToDatabase(book)
            }
        }
    }

    fun getBooksFromDatabaseVM(): LiveData<List<Book>> = booksDatabase

}