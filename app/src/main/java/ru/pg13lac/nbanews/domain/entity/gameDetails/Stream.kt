package ru.pg13lac.nbanews.domain.entity.gameDetails

data class Stream(
    val isOnAir: Boolean,
    val language: String,
    val streamId: String
)