package com.example.nit3213assessment2.data

data class Entity (
    val title: String,
    val author: String,
    val genre: String,
    val publicationYear: String,
    val description: String,
)

data class ResponseItem(
    val entities: List<Entity>,
    val entityTotal: Int
)