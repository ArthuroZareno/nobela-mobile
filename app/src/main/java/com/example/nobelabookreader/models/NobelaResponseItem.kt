package com.example.nobelabookreader.models

data class NobelaResponseItem(
    val body: String,
    val created_at: String,
    val description: String,
    val genre: String,
    val id: Int,
    val image: String,
    val rating: String,
    val title: String,
    val updated_at: String
)