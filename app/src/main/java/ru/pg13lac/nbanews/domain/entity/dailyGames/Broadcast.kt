package ru.pg13lac.nbanews.domain.entity.dailyGames

data class Broadcast(
    val channel: String,
    val locale: String,
    val network: String,
    val type: String
)