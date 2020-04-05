package ru.pg13lac.nbanews.domain.entity.testGames

data class VTeam(
    val fullName: String,
    val logo: String,
    val nickName: String,
    val score: ScoreX,
    val shortName: String,
    val teamId: String
)