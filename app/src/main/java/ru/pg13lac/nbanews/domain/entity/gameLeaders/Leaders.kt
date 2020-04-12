package ru.pg13lac.nbanews.domain.entity.gameLeaders

data class Leaders(
    val assists: List<Assist>,
    val points: List<Point>,
    val rebounds: List<Rebound>
)