package ru.pg13lac.nbanews.data.repository

import ru.pg13lac.nbanews.data.service.Api
import javax.inject.Inject

class GamesRepository @Inject constructor(private val api: Api) {
    fun getGames(day: String) = api.getGames(day)

    fun getBoxScore(gameId: String) = api.getBoxScore(gameId)
}
