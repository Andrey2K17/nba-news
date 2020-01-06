package ru.pg13lac.nbanews.domain.entity.scoreboard

data class Broadcasters(
    val canadian: List<Canadian>,
    val hTeam: List<HTeamXX>,
    val national: List<NationalX>,
    val spanish_hTeam: List<Any>,
    val spanish_national: List<Any>,
    val spanish_vTeam: List<Any>,
    val vTeam: List<Any>
)