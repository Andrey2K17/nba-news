package ru.pg13lac.nbanews.domain.entity.summaryGameDetails

data class MostUnanswered(
    val opp_score: Int,
    val own_score: Int,
    val points: Int
)