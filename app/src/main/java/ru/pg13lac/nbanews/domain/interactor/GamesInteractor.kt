package ru.pg13lac.nbanews.domain.interactor

import ru.pg13lac.nbanews.data.repository.GamesRepository
import javax.inject.Inject

class GamesInteractor @Inject constructor(private val repository: GamesRepository) {

    fun getDailyGames(year: String, month: String, day: String) =
        repository.getDailyGames(year, month, day)

    fun getSummaryGameDetails(gameId: String) = repository.getSummaryGameDetails(gameId)

    fun getGameBoxScore(gameId: String) = repository.getGameBoxScore(gameId)
}