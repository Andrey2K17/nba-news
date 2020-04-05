package ru.pg13lac.nbanews.domain.entity.testGames

data class HTeam(
    val fullName: String,
    val logo: String,
    val nickName: String,
    val score: Score,
    val shortName: String,
    val teamId: String
)