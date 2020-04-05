package ru.pg13lac.nbanews.data.service

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import ru.pg13lac.nbanews.domain.entity.testGames.TestGames

interface TestApi {
    @Headers("x-rapidapi-host: api-nba-v1.p.rapidapi.com",
    "x-rapidapi-key: b3437a9e5fmsh20748d394fe61a2p135610jsn613a69156d62")
    @GET("games/date/{date}")
    fun getGames(
        @Path("date") date: String
    ): Single<TestGames>
}