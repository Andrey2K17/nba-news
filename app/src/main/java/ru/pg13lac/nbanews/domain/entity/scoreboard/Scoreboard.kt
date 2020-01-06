package ru.pg13lac.nbanews.domain.entity.scoreboard

data class Scoreboard(
    val _internal: Internal,
    val games: List<Game>,
    val numGames: Int
)