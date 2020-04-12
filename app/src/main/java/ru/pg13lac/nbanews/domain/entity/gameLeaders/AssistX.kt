package ru.pg13lac.nbanews.domain.entity.gameLeaders

data class AssistX(
    val full_name: String,
    val id: String,
    val jersey_number: String,
    val position: String,
    val primary_position: String,
    val reference: String,
    val sr_id: String,
    val statistics: StatisticsXXX
)