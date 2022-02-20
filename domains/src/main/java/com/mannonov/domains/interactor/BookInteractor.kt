package com.mannonov.domains.interactor

import com.mannonov.domains.models.BaseResponse
import com.mannonov.domains.models.Book
import com.mannonov.domains.repository.BookRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class BookInteractor @Inject constructor(private val bookRepository: BookRepository) {

    fun getBooks(count: Int): Flow<Result<BaseResponse<Book>>> {
        return bookRepository.getBooks(count).map {
            Result.success(it)
        }.catch {
            emit(Result.failure(it))
        }.flowOn(Dispatchers.IO)
    }

    fun getBooksFromDatabase(): List<Book> = bookRepository.getBooksFromDatabase()

    suspend fun insertBookToDatabase(book: Book) = bookRepository.addBookToDatabase(book)


}