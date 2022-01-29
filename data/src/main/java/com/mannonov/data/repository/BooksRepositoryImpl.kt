package com.mannonov.data.repository

import com.mannonov.data.network.ApiService
import com.mannonov.domains.models.BaseResponse
import com.mannonov.domains.models.Book
import com.mannonov.domains.repository.BookRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class BooksRepositoryImpl @Inject constructor(private val apiService: ApiService) : BookRepository {
    override fun getBooks(count: Int): Flow<BaseResponse<Book>> {
        return apiService.getBooks(count)
    }


}