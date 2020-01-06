package ru.pg13lac.nbanews.domain.entity.scoreboard

data class VTeam(
    val linescore: List<LinescoreX>,
    val loss: String,
    val score: String,
    val seriesLoss: String,
    val seriesWin: String,
    val teamId: String,
    val triCode: String,
    val win: String
)