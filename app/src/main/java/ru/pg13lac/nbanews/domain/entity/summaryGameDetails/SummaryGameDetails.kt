package ru.pg13lac.nbanews.domain.entity.summaryGameDetails

data class SummaryGameDetails(
    val attendance: Int,
    val away: Away,
    val clock: String,
    val clock_decimal: String,
    val coverage: String,
    val duration: String,
    val entry_mode: String,
    val home: Home,
    val id: String,
    val lead_changes: Int,
    val officials: List<Official>,
    val quarter: Int,
    val reference: String,
    val scheduled: String,
    val sr_id: String,
    val status: String,
    val time_zones: TimeZones,
    val times_tied: Int,
    val track_on_court: Boolean,
    val venue: Venue
)