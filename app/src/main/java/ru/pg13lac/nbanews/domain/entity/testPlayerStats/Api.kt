package ru.pg13lac.nbanews.domain.entity.testPlayerStats

data class Api(
    val filters: List<String>,
    val message: String,
    val results: Int,
    val statistics: List<Statistic>,
    val status: Int
)