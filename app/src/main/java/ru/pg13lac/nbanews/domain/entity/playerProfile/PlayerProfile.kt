package ru.pg13lac.nbanews.domain.entity.playerProfile

data class PlayerProfile(
    val abbr_name: String,
    val birth_place: String,
    val birthdate: String,
    val draft: Draft,
    val experience: String,
    val first_name: String,
    val full_name: String,
    val height: Int,
    val id: String,
    val jersey_number: String,
    val last_name: String,
    val league: League,
    val position: String,
    val primary_position: String,
    val reference: String,
    val rookie_year: Int,
    val seasons: List<Season>,
    val sr_id: String,
    val status: String,
    val team: TeamX,
    val updated: String,
    val weight: Int
)