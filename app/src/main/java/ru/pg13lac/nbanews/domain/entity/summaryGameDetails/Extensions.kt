package ru.pg13lac.nbanews.domain.entity.summaryGameDetails

import ru.pg13lac.nbanews.domain.entity.*

fun SummaryGameDetails.toGameDetails(): GameDetails {
    val teamComparison = Pair(
        first = TeamComparison(
            TEAM_ABBREVIATION = this.home.alias,
            FGM = this.home.statistics.field_goals_made.toString(),
            FGA = this.home.statistics.field_goals_att.toString(),
            FG_PCT = this.home.statistics.field_goals_pct.toString(),
            FG3M = this.home.statistics.three_points_made.toString(),
            FG3A = this.home.statistics.three_points_att.toString(),
            FG3_PCT = this.home.statistics.three_points_pct.toString(),
            FTM = this.home.statistics.free_throws_made.toString(),
            FTA = this.home.statistics.free_throws_att.toString(),
            FT_PCT = this.home.statistics.free_throws_pct.toString(),
            AST = this.home.statistics.assists.toString(),
            REB = this.home.statistics.rebounds.toString(),
            O_REB = this.home.statistics.offensive_rebounds.toString(),
            D_REB = this.home.statistics.defensive_rebounds.toString(),
            STL = this.home.statistics.steals.toString(),
            BLK = this.home.statistics.blocks.toString(),
            TO = this.home.statistics.turnovers.toString()
        ),
        second = TeamComparison(
            TEAM_ABBREVIATION = this.away.alias,
            FGM = this.away.statistics.field_goals_made.toString(),
            FGA = this.away.statistics.field_goals_att.toString(),
            FG_PCT = this.away.statistics.field_goals_pct.toString(),
            FG3M = this.away.statistics.three_points_made.toString(),
            FG3A = this.away.statistics.three_points_att.toString(),
            FG3_PCT = this.away.statistics.three_points_pct.toString(),
            FTM = this.away.statistics.free_throws_made.toString(),
            FTA = this.away.statistics.free_throws_att.toString(),
            FT_PCT = this.away.statistics.free_throws_pct.toString(),
            AST = this.away.statistics.assists.toString(),
            REB = this.away.statistics.rebounds.toString(),
            O_REB = this.away.statistics.offensive_rebounds.toString(),
            D_REB = this.away.statistics.defensive_rebounds.toString(),
            STL = this.away.statistics.steals.toString(),
            BLK = this.away.statistics.blocks.toString(),
            TO = this.away.statistics.turnovers.toString()
        )
    )

    val gameDetailsInfo = GameDetailsInfo(
        gameId = this.id,
        leftTeam = this.home.alias,
        rightTeam = this.away.alias
    )

    val homeBoxScore = mutableListOf<BoxScore>()
    val awayBoxScore = mutableListOf<BoxScore>()

    for (i in this.home.players.indices) {
        homeBoxScore.add(
            BoxScore(
                team_name = this.home.name,
                player_name = this.home.players[i].first_name,
                player_surname = this.home.players[i].last_name,
                player_pts = this.home.players[i].statistics.points.toString(),
                player_id = this.home.players[i].reference,
                player_ast = this.home.players[i].statistics.assists.toString(),
                player_reb = this.home.players[i].statistics.rebounds.toString()
            )
        )
    }

    for (i in this.away.players.indices) {
        awayBoxScore.add(
            BoxScore(
                team_name = this.away.name,
                player_name = this.away.players[i].first_name,
                player_surname = this.away.players[i].last_name,
                player_pts = this.away.players[i].statistics.points.toString(),
                player_id = this.away.players[i].reference,
                player_ast = this.away.players[i].statistics.assists.toString(),
                player_reb = this.away.players[i].statistics.rebounds.toString()
            )
        )
    }

    val boxScoreList = Pair(first = homeBoxScore, second = awayBoxScore)

    return GameDetails(boxScoreList, teamComparison, gameDetailsInfo)
}

fun SummaryGameDetails.toTeamPointsForQuarter() = TeamPointsForQuarter(
    left_team_q1 = this.home.scoring[0].points.toString(),
    left_team_q2 = this.home.scoring[1].points.toString(),
    left_team_q3 = this.home.scoring[2].points.toString(),
    left_team_q4 = this.home.scoring[3].points.toString(),
    left_team_tot = this.home.points.toString(),
    right_team_q1 = this.away.scoring[0].points.toString(),
    right_team_q2 = this.away.scoring[1].points.toString(),
    right_team_q3 = this.away.scoring[2].points.toString(),
    right_team_q4 = this.away.scoring[3].points.toString(),
    right_team_tot = this.away.points.toString(),
    game_id = this.id,
    leftTeam = this.home.alias,
    rightTeam = this.away.alias
)