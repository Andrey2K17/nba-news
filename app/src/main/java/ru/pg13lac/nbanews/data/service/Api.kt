package ru.pg13lac.nbanews.data.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.pg13lac.nbanews.App
import ru.pg13lac.nbanews.domain.entity.dailyGames.DailyGames
import ru.pg13lac.nbanews.domain.entity.gameLeaders.GameBoxScoreLeaders
import ru.pg13lac.nbanews.domain.entity.summaryGameDetails.SummaryGameDetails

interface Api {

    @GET("/nba/trial/v7/ru/games/{year}/{month}/{day}/schedule.json?api_key=${App.API_KEY}")
    fun getDailyGames(
        @Path("year") year: String,
        @Path("month") month: String,
        @Path("day") day: String
    ): Single<DailyGames>

    @GET("nba/trial/v7/ru/games/{gameId}/summary.json?api_key=${App.API_KEY}")
    fun getSummaryGameDetails(
        @Path("gameId") gameId: String
    ): Single<SummaryGameDetails>

    @GET("nba/trial/v7/ru/games/{gameId}/boxscore.json?api_key=${App.API_KEY}")
    fun getGameBoxScore(
        @Path("gameId") gameId: String
    ): Single<GameBoxScoreLeaders>
}