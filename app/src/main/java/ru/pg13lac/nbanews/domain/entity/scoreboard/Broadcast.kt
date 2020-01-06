package ru.pg13lac.nbanews.domain.entity.scoreboard

data class Broadcast(
    val audio: Audio,
    val broadcasters: Broadcasters,
    val video: Video
)