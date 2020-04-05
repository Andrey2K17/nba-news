package ru.pg13lac.nbanews.domain.entity.testGameDetails

data class Api(
    val filters: List<String>,
    val game: List<Game>,
    val message: String,
    val results: Int,
    val status: Int
)