package ru.pg13lac.nbanews.domain.entity.summaryGameDetails

data class PlayerX(
    val active: Boolean,
    val first_name: String,
    val fouled_out: Boolean,
    val full_name: String,
    val id: String,
    val jersey_number: String,
    val last_name: String,
    val name_suffix: String,
    val not_playing_description: String,
    val not_playing_reason: String,
    val on_court: Boolean,
    val played: Boolean,
    val position: String,
    val primary_position: String,
    val reference: String,
    val sr_id: String,
    val starter: Boolean,
    val statistics: StatisticsXX
)