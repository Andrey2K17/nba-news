package ru.pg13lac.nbanews.domain.interactor

import ru.pg13lac.nbanews.data.repository.GamesRepository
import javax.inject.Inject

class GamesInteractor @Inject constructor (
    private val repository: GamesRepository
) {
    fun getGames(day: String) =
        repository.getGames(day)
}