package ru.pg13lac.nbanews.data.repository

import ru.pg13lac.nbanews.data.service.Api
import javax.inject.Inject

class GamesRepository @Inject constructor(private val api: Api) {

    fun getGameDetails(date: String, gameId: String) = api.getGameDetails(date, gameId)

    fun getGames(date: String) = api.getGames(date)
}

