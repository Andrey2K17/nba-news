package ru.pg13lac.nbanews.domain.entity.gameDetails

data class Stats(
    val activePlayers: List<ActivePlayer>,
    val hTeam: HTeamXXX,
    val leadChanges: String,
    val timesTied: String,
    val vTeam: VTeamXXX
)