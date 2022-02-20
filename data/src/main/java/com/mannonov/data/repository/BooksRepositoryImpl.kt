package com.mannonov.data.repository

import com.mannonov.data.database.AppDatabase
import com.mannonov.data.database.dao.AppDao
import com.mannonov.data.database.entitiy.BookDatabaseModel
import com.mannonov.data.network.RemoteDataSource
import com.mannonov.domains.models.BaseResponse
import com.mannonov.domains.models.Book
import com.mannonov.domains.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val appDao: AppDao,
) : BookRepository {
    override fun getBooks(count: Int): Flow<BaseResponse<Book>> {
        return remoteDataSource.getBooks(count)
    }

    override fun getBooksFromDatabase(): List<Book> {
        return appDao.getBooks().map {
            Book(author = it.author,
                description = it.description,
                genre = it.genre,
                id = it.id,
                image = it.image,
                isbn = it.isbn,
                published = it.published,
                publisher = it.publisher,
                title = it.title)
        }
    }

    override suspend fun addBookToDatabase(book: Book) {
        appDao
            .insertBook(bookDatabaseModel = BookDatabaseModel(author = book.author,
                description = book.description,
                genre = book.genre,
                id = book.id,
                image = book.image,
                isbn = book.isbn,
                published = book.published,
                publisher = book.publisher,
                title = book.title))
    }
}