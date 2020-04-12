package ru.pg13lac.nbanews.domain.entity.dailyGames

data class DailyGames(
    val date: String,
    val games: List<Game>,
    val league: League
)