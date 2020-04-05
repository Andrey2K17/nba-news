package ru.pg13lac.nbanews.domain.entity.testPlayerStats

import ru.pg13lac.nbanews.domain.entity.*
import ru.pg13lac.nbanews.domain.entity.testCompleteStats.TestCompleteStats

fun TestCompleteStats.toGameDetails(): GameDetails {
    val vTeamStats = this.testGameStatistic.api.statistics[0]
    val hTeamStats = this.testGameStatistic.api.statistics[1]
    val teamComparison = Pair(
        first = TeamComparison(
            TEAM_ABBREVIATION = this.testGameDetails.api.game[0].vTeam.shortName,
            FGM = vTeamStats.fgm,
            FGA = vTeamStats.fga,
            FG_PCT = vTeamStats.fgp,
            FG3M = vTeamStats.tpm,
            FG3A = vTeamStats.tpa,
            FG3_PCT = vTeamStats.tpp,
            FTM = vTeamStats.ftm,
            FTA = vTeamStats.fta,
            FT_PCT = vTeamStats.ftp,
            AST = vTeamStats.assists,
            REB = vTeamStats.totReb,
            O_REB = vTeamStats.offReb,
            D_REB = vTeamStats.defReb,
            STL = vTeamStats.steals,
            BLK = vTeamStats.blocks,
            TO = vTeamStats.turnovers
        ),
        second = TeamComparison(
            TEAM_ABBREVIATION = this.testGameDetails.api.game[0].hTeam.shortName,
            FGM = hTeamStats.fgm,
            FGA = hTeamStats.fga,
            FG_PCT = hTeamStats.fgp,
            FG3M = hTeamStats.tpm,
            FG3A = hTeamStats.tpa,
            FG3_PCT = hTeamStats.tpp,
            FTM = hTeamStats.ftm,
            FTA = hTeamStats.fta,
            FT_PCT = hTeamStats.ftp,
            AST = hTeamStats.assists,
            REB = hTeamStats.totReb,
            O_REB = hTeamStats.offReb,
            D_REB = hTeamStats.defReb,
            STL = hTeamStats.steals,
            BLK = hTeamStats.blocks,
            TO = hTeamStats.turnovers
        )
    )
    val gameDetailsInfo = GameDetailsInfo(
        gameId = this.testGameDetails.api.game[0].gameId,
        leftTeam = this.testGameDetails.api.game[0].vTeam.nickname,
        rightTeam = this.testGameDetails.api.game[0].hTeam.nickname
    )

    val boxScoreList: MutableList<BoxScore> =
        mutableListOf(
            BoxScore(
                "asd", "a",
                "a", "a", "a", "a", "a"
            )
        )

    return GameDetails(
        boxScoreList,
        teamComparison,
        gameDetailsInfo
    )
}

fun TestCompleteStats.toTeamPointsForQuarter() = TeamPointsForQuarter(
    left_team_q1 = this.testGameDetails.api.game[0].vTeam.score.linescore[0],
    left_team_q2 = this.testGameDetails.api.game[0].vTeam.score.linescore[1],
    left_team_q3 = this.testGameDetails.api.game[0].vTeam.score.linescore[2],
    left_team_q4 = this.testGameDetails.api.game[0].vTeam.score.linescore[3],
    left_team_tot = this.testGameDetails.api.game[0].vTeam.score.points,
    right_team_q1 = this.testGameDetails.api.game[0].hTeam.score.linescore[0],
    right_team_q2 = this.testGameDetails.api.game[0].hTeam.score.linescore[1],
    right_team_q3 = this.testGameDetails.api.game[0].hTeam.score.linescore[2],
    right_team_q4 = this.testGameDetails.api.game[0].hTeam.score.linescore[3],
    right_team_tot = this.testGameDetails.api.game[0].hTeam.score.points,
    game_id = this.testGameDetails.api.game[0].gameId,
    leftTeam = this.testGameDetails.api.game[0].vTeam.nickname,
    rightTeam = this.testGameDetails.api.game[0].hTeam.nickname
)

fun TestCompleteStats.toGameLeaders() : Pair<GameLeaders, GameLeaders> {
    val vLeaders = this.testGameDetails.api.game[0].vTeam.leaders
    val vPlayerPoint = vLeaders.filter { it.points !== null }.maxBy { it.points!!.toInt() }
    val vPlayerRebounds = vLeaders.filter { it.rebounds !== null }.maxBy { it.rebounds!!.toInt() }
    val vPlayerAssist = vLeaders.filter { it.assists !== null }.maxBy { it.assists!!.toInt() }
    val hLeaders = this.testGameDetails.api.game[0].hTeam.leaders
    val hPlayerPoint = hLeaders.filter { it.points !== null }.maxBy { it.points!!.toInt() }
    val hPlayerRebounds = hLeaders.filter { it.rebounds !== null }.maxBy { it.rebounds!!.toInt() }
    val hPlayerAssist = hLeaders.filter { it.assists !== null }.maxBy { it.assists!!.toInt() }
    return Pair(
        GameLeaders(
            game_id = this.testGameDetails.api.game[0].gameId,
            player_pts = Points(
                id = vPlayerPoint?.playerId?: "",
                name = vPlayerPoint?.name?: "",
                amount = vPlayerPoint?.points?: ""
            ),
            player_reb = Rebounds(
                id = vPlayerRebounds?.playerId?: "",
                name = vPlayerRebounds?.name?: "",
                amount = vPlayerRebounds?.rebounds?: ""
            ),
            player_ast = Assists(
                id = vPlayerAssist?.playerId?: "",
                name = vPlayerAssist?.name?: "",
                amount = vPlayerAssist?.assists?: ""
            ),
            team_name = this.testGameDetails.api.game[0].vTeam.nickname
        ),
        GameLeaders(
            game_id = this.testGameDetails.api.game[0].gameId,
            player_pts = Points(
                id = hPlayerPoint?.playerId?: "",
                name = hPlayerPoint?.name?: "",
                amount = hPlayerPoint?.points?: ""
            ),
            player_reb = Rebounds(
                id = hPlayerRebounds?.playerId?: "",
                name = hPlayerRebounds?.name?: "",
                amount = hPlayerRebounds?.rebounds?: ""
            ),
            player_ast = Assists(
                id = hPlayerAssist?.playerId?: "",
                name = hPlayerAssist?.name?: "",
                amount = hPlayerAssist?.assists?: ""
            ),
            team_name = this.testGameDetails.api.game[0].hTeam.nickname
        )
    )
}