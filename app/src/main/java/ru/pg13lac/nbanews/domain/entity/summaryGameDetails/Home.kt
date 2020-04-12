package ru.pg13lac.nbanews.domain.entity.summaryGameDetails

data class Home(
    val alias: String,
    val bonus: Boolean,
    val coaches: List<CoacheX>,
    val id: String,
    val market: String,
    val name: String,
    val players: List<PlayerX>,
    val points: Int,
    val reference: String,
    val scoring: List<ScoringX>,
    val sr_id: String,
    val statistics: StatisticsXXX
)