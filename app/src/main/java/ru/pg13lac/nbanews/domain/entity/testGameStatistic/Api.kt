package ru.pg13lac.nbanews.domain.entity.testGameStatistic

data class Api(
    val filters: List<String>,
    val message: String,
    val results: Int,
    val statistics: List<Statistic>,
    val status: Int
)