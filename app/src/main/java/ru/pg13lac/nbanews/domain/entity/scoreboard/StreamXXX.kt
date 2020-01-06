package ru.pg13lac.nbanews.domain.entity.scoreboard

data class StreamXXX(
    val doesArchiveExist: Boolean,
    val duration: Int,
    val isArchiveAvailToWatch: Boolean,
    val isOnAir: Boolean,
    val streamId: String,
    val streamType: String
)