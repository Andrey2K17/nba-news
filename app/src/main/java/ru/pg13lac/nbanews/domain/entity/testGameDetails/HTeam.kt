package ru.pg13lac.nbanews.domain.entity.testGameDetails

data class HTeam(
    val allStar: String,
    val fullName: String,
    val leaders: List<Leader>,
    val logo: String,
    val nbaFranchise: String,
    val nickname: String,
    val score: Score,
    val shortName: String,
    val teamId: String
)