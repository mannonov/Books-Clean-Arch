package com.mannonov.data.network

import com.mannonov.domains.models.BaseResponse
import com.mannonov.domains.models.Book
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("books")
    fun getBooks(@Query("_quantity") count: Int): Flow<BaseResponse<Book>>
}