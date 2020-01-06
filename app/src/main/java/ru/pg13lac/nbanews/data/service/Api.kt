package ru.pg13lac.nbanews.data.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.pg13lac.nbanews.domain.entity.scoreboard.Scoreboard
import ru.pg13lac.nbanews.domain.entity.gameDetails.GameDetails

interface Api {

    // Получение списка всех игр в указанный день

    @GET("{date}/scoreboard.json")
    fun getGames(
        @Path("date") date: String
    ): Single<Scoreboard>

    // Получение подробной статистики за матч

    @GET("{date}/{gameId}_boxscore.json")
    fun getGameDetails(
        @Path("date") date: String,
        @Path("gameId") gameId: String
    ): Single<GameDetails>
}