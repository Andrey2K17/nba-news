package ru.pg13lac.nbanews.domain.interactor

import ru.pg13lac.nbanews.data.repository.GamesRepository
import javax.inject.Inject


class GamesInteractor @Inject constructor(private val repository: GamesRepository) {

    fun getGameDetails(date: String, gameId: String) = repository.getGameDetails(date, gameId)

    fun getGames(date: String) = repository.getGames(date)

    fun getTestGameDetails(gameId: String) = repository.getTestGameDetails(gameId)

    fun getTestGameStatistic(gameId: String) = repository.getTestGameStatistic(gameId)

    fun getTestPlayerStatistic(gameId: String) = repository.getTestPlayerStatistic(gameId)
}