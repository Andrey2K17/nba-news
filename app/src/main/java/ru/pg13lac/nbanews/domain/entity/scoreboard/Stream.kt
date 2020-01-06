package ru.pg13lac.nbanews.domain.entity.scoreboard

data class Stream(
    val isOnAir: Boolean,
    val language: String,
    val streamId: String
)