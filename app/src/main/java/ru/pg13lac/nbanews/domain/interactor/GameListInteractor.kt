package ru.pg13lac.nbanews.domain.interactor

import ru.pg13lac.nbanews.data.repository.GameListRepository

class GameListInteractor (
    private val repository: GameListRepository
) {
    fun getGames(day: String) =
        repository.getGames(day)
}