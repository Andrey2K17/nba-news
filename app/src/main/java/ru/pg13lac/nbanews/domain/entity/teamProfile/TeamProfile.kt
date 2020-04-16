package ru.pg13lac.nbanews.domain.entity.teamProfile

data class TeamProfile(
    val alias: String,
    val coaches: List<Coache>,
    val conference: Conference,
    val division: Division,
    val founded: Int,
    val id: String,
    val league: League,
    val market: String,
    val name: String,
    val players: List<Player>,
    val reference: String,
    val sr_id: String,
    val venue: Venue
)