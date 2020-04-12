package ru.pg13lac.nbanews.domain.entity.summaryGameDetails

data class Away(
    val alias: String,
    val bonus: Boolean,
    val coaches: List<Coache>,
    val id: String,
    val market: String,
    val name: String,
    val players: List<Player>,
    val points: Int,
    val reference: String,
    val scoring: List<Scoring>,
    val sr_id: String,
    val statistics: StatisticsX
)