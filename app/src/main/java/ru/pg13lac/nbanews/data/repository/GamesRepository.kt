package ru.pg13lac.nbanews.data.repository

import ru.pg13lac.nbanews.data.service.Api
import javax.inject.Inject

class GamesRepository @Inject constructor(private val api: Api) {

    fun getDailyGames(year: String, month: String, day: String) =
        api.getDailyGames(year, month, day)

    fun getSummaryGameDetails(gameId: String) = api.getSummaryGameDetails(gameId)

    fun getGameBoxScore(gameId: String) = api.getGameBoxScore(gameId)

    fun getTeamProfile(teamId: String) = api.getTeamProfile(teamId)

    fun getPlayerProfile(playerId: String) = api.getPlayerProfile(playerId)
}

