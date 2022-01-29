package com.mannonov.domains.repository

import com.mannonov.domains.models.BaseResponse
import com.mannonov.domains.models.Book
import kotlinx.coroutines.flow.Flow

interface BookRepository {

    fun getBooks(count: Int): Flow<BaseResponse<Book>>

}