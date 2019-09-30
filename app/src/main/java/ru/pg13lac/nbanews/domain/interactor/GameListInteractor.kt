package ru.pg13lac.nbanews.domain.interactor

import ru.pg13lac.nbanews.data.repository.GameListRepository
import javax.inject.Inject

class GameListInteractor @Inject constructor (
    private val repository: GameListRepository
) {
    fun getGames(day: String) =
        repository.getGames(day)
}