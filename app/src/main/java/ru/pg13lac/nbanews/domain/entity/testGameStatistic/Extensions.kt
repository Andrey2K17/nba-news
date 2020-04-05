package ru.pg13lac.nbanews.domain.entity.testGameStatistic

import ru.pg13lac.nbanews.domain.entity.BoxScore
import ru.pg13lac.nbanews.domain.entity.testPlayerStats.TestPlayerStats

fun TestPlayerStats.toBoxScore(): List<BoxScore> {
    val boxScoreList = mutableListOf<BoxScore>()
    for (i in this.api.statistics.indices) {
        boxScoreList.add(
            BoxScore(
                team_name = this.api.statistics[i].teamId,
                player_name = this.api.statistics[i].playerId,
                player_surname = this.api.statistics[i].playerId,
                player_pts = this.api.statistics[i].points,
                player_id = this.api.statistics[i].playerId,
                player_ast = this.api.statistics[i].assists,
                player_reb = this.api.statistics[i].totReb
            )
        )
    }
    return boxScoreList
}