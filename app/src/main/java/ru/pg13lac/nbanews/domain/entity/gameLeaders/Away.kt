package ru.pg13lac.nbanews.domain.entity.gameLeaders

data class Away(
    val alias: String,
    val bonus: Boolean,
    val id: String,
    val leaders: Leaders,
    val market: String,
    val name: String,
    val points: Int,
    val reference: String,
    val scoring: List<Scoring>,
    val sr_id: String
)