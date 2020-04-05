package ru.pg13lac.nbanews.data.repository

import ru.pg13lac.nbanews.data.service.Api
import javax.inject.Inject

class GamesRepository @Inject constructor(private val api: Api) {

    fun getGameDetails(date: String, gameId: String) = api.getGameDetails(date, gameId)

    fun getGames(date: String) = api.getGames(date)

    fun getTestGameDetails(gameId: String) = api.getTestGameDetails(gameId)

    fun getTestGameStatistic(gameId: String) = api.getTestGameStatistic(gameId)

    fun getTestPlayerStatistic(gameId: String) = api.getTestPlayerStatistic(gameId)
}

