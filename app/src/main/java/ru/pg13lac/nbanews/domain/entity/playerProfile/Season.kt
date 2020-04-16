package ru.pg13lac.nbanews.domain.entity.playerProfile

data class Season(
    val id: String,
    val teams: List<Team>,
    val type: String,
    val year: Int
)