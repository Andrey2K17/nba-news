package ru.pg13lac.nbanews.domain.entity.playerProfile

data class Draft(
    val pick: String,
    val round: String,
    val team_id: String,
    val year: Int
)