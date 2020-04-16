package ru.pg13lac.nbanews.domain.entity.playerProfile

data class Team(
    val alias: String,
    val average: Average,
    val id: String,
    val market: String,
    val name: String,
    val reference: String,
    val sr_id: String,
    val total: Total
)