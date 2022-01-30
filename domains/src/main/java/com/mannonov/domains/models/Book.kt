package com.mannonov.domains.models

import java.io.Serializable

data class Book(
    val author: String,
    val description: String,
    val genre: String,
    val id: Int,
    val image: String,
    val isbn: String,
    val published: String,
    val publisher: String,
    val title: String,
) : Serializable