package ru.pg13lac.nbanews.domain.entity.testGameDetails

data class VTeam(
    val allStar: String,
    val fullName: String,
    val leaders: List<Leader>,
    val logo: String,
    val nbaFranchise: String,
    val nickname: String,
    val score: ScoreX,
    val shortName: String,
    val teamId: String
)