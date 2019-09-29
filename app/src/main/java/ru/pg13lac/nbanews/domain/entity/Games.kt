package ru.pg13lac.nbanews.domain.entity

import com.google.gson.annotations.SerializedName

data class Games(
    @SerializedName("resource") val resource: String?,
    @SerializedName("parameters") val parameters: Parameters?,
    @SerializedName("resultSets") val resultSets: List<ResultSet>
)

data class Parameters(
    @SerializedName("GameDate") val GameDate: String?,
    @SerializedName("LeagueID") val LeagueID: String?,
    @SerializedName("DayOffset") val DayOffset: String?
)

data class ResultSet(
    @SerializedName("name") val name: String?,
    @SerializedName("headers") val headers: List<String>?,
    @SerializedName("rowSet") val rowSet: List<List<String>>?
)