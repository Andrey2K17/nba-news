package ru.pg13lac.nbanews.domain.entity.gameLeaders

data class Scoring(
    val number: Int,
    val points: Int,
    val sequence: Int,
    val type: String
)