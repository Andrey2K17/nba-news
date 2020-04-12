package ru.pg13lac.nbanews.domain.entity.dailyGames

data class Game(
    val away: Away,
    val away_points: Int,
    val broadcasts: List<Broadcast>,
    val coverage: String,
    val home: Home,
    val home_points: Int,
    val id: String,
    val reference: String,
    val scheduled: String,
    val sr_id: String,
    val status: String,
    val time_zones: TimeZones,
    val track_on_court: Boolean,
    val venue: Venue
)