package com.mannonov.domains.models

data class BaseResponse<T>(
    val code: Int,
    val data: List<T>,
    val status: String,
    val total: Int
)