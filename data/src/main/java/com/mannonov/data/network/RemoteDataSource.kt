package com.mannonov.data.network

import com.mannonov.domains.models.BaseResponse
import com.mannonov.domains.models.Book
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService){

    fun getBooks(count: Int): Flow<BaseResponse<Book>> {
        return apiService.getBooks(count)
    }

}