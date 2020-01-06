package ru.pg13lac.nbanews.domain.entity.gameDetails

data class VTeamXXX(
    val biggestLead: String,
    val fastBreakPoints: String,
    val leaders: LeadersX,
    val longestRun: String,
    val pointsInPaint: String,
    val pointsOffTurnovers: String,
    val secondChancePoints: String,
    val totals: TotalsX
)