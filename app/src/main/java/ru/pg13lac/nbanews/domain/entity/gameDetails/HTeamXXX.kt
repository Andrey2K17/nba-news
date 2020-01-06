package ru.pg13lac.nbanews.domain.entity.gameDetails

data class HTeamXXX(
    val biggestLead: String,
    val fastBreakPoints: String,
    val leaders: Leaders,
    val longestRun: String,
    val pointsInPaint: String,
    val pointsOffTurnovers: String,
    val secondChancePoints: String,
    val totals: Totals
)