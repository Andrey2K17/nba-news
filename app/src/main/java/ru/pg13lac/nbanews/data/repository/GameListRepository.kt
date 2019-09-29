package ru.pg13lac.nbanews.data.repository

import ru.pg13lac.nbanews.data.service.Api

class GameListRepository(private val api: Api) {
    fun getGames(day: String) = api.getGames(day)
}

