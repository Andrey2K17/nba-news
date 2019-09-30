package ru.pg13lac.nbanews.data.repository

import ru.pg13lac.nbanews.data.service.Api
import javax.inject.Inject

class GameListRepository @Inject constructor(private val api: Api) {
    fun getGames(day: String) = api.getGames(day)
}

