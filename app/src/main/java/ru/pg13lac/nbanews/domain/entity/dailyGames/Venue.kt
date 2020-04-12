package ru.pg13lac.nbanews.domain.entity.dailyGames

data class Venue(
    val address: String,
    val capacity: Int,
    val city: String,
    val country: String,
    val id: String,
    val name: String,
    val sr_id: String,
    val state: String,
    val zip: String
)