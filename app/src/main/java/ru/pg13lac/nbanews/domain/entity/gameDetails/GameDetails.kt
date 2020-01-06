package ru.pg13lac.nbanews.domain.entity.gameDetails

data class GameDetails(
    val _internal: Internal,
    val basicGameData: BasicGameData,
    val previousMatchup: PreviousMatchup,
    val stats: Stats
)