package ru.pg13lac.nbanews.data.repository

import ru.pg13lac.nbanews.data.service.TestApi
import ru.pg13lac.nbanews.data.service.TestApiHolder

class GameTestRepository {
    fun getTestGames(date: String) = TestApiHolder().api.getGames(date)
}