package com.mannonov.myapplication.viewmodel

import com.mannonov.domains.models.Book

sealed class BookResource {

    object Loading : BookResource()

    data class Success(val list: List<Book>) : BookResource()

    data class Error(val message: String?) : BookResource()

}