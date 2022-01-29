package com.mannonov.domains.models

data class BookResponse(
    val code: Int,
    val `data`: List<Data>,
    val status: String,
    val total: Int
)