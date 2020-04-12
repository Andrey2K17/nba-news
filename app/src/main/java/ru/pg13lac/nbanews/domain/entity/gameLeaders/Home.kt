package ru.pg13lac.nbanews.domain.entity.gameLeaders

data class Home(
    val alias: String,
    val bonus: Boolean,
    val id: String,
    val leaders: LeadersX,
    val market: String,
    val name: String,
    val points: Int,
    val reference: String,
    val scoring: List<ScoringX>,
    val sr_id: String
)