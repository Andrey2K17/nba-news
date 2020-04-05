package ru.pg13lac.nbanews.data.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import ru.pg13lac.nbanews.domain.entity.gameDetails.GameDetails
import ru.pg13lac.nbanews.domain.entity.testGameDetails.TestGameDetails
import ru.pg13lac.nbanews.domain.entity.testGameStatistic.TestGameStatistic
import ru.pg13lac.nbanews.domain.entity.testGames.TestGames
import ru.pg13lac.nbanews.domain.entity.testPlayerStats.TestPlayerStats

interface Api {

//    // Получение списка всех игр в указанный день
//
//    @GET("{date}/scoreboard.json")
//    fun getGames(
//        @Path("date") date: String
//    ): Single<Scoreboard>

    // Получение подробной статистики за матч

    @GET("{date}/{gameId}_boxscore.json")
    fun getGameDetails(
        @Path("date") date: String,
        @Path("gameId") gameId: String
    ): Single<GameDetails>

    // Получение списка всех игр в указанный день
    @Headers("x-rapidapi-host: api-nba-v1.p.rapidapi.com",
        "x-rapidapi-key: b3437a9e5fmsh20748d394fe61a2p135610jsn613a69156d62")
    @GET("games/date/{date}")
    fun getGames(
        @Path("date") date: String
    ): Single<TestGames>

    // Получение деталей игры
    @Headers("x-rapidapi-host: api-nba-v1.p.rapidapi.com",
        "x-rapidapi-key: b3437a9e5fmsh20748d394fe61a2p135610jsn613a69156d62")
    @GET("gameDetails/{gameId}")
    fun getTestGameDetails(
        @Path("gameId") gameId: String
    ): Single<TestGameDetails>

    // Получение деталей игры
    @Headers("x-rapidapi-host: api-nba-v1.p.rapidapi.com",
        "x-rapidapi-key: b3437a9e5fmsh20748d394fe61a2p135610jsn613a69156d62")
    @GET("statistics/games/gameId/{gameId}")
    fun getTestGameStatistic(
        @Path("gameId") gameId: String
    ): Single<TestGameStatistic>

    // Получение статистики игроков по игре
    @Headers("x-rapidapi-host: api-nba-v1.p.rapidapi.com",
        "x-rapidapi-key: b3437a9e5fmsh20748d394fe61a2p135610jsn613a69156d62")
    @GET("statistics/players/gameId/{gameId}")
    fun getTestPlayerStatistic(
        @Path("gameId") gameId: String
    ): Single<TestPlayerStats>
}