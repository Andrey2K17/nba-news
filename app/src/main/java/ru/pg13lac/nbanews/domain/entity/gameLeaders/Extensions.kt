package ru.pg13lac.nbanews.domain.entity.gameLeaders

import ru.pg13lac.nbanews.domain.entity.*

fun GameBoxScoreLeaders.toLeaders() : Pair<GameLeaders, GameLeaders>  {
        return Pair(
            GameLeaders(
                game_id = this.id,
                player_pts = Points(
                    id = this.home.leaders.points[0].reference,
                    name = this.home.leaders.points[0].full_name,
                    amount = this.home.leaders.points[0].statistics.points.toString()
                ),
                player_reb = Rebounds(
                    id = this.home.leaders.rebounds[0].reference,
                    name = this.home.leaders.rebounds[0].full_name,
                    amount = this.home.leaders.rebounds[0].statistics.rebounds.toString()
                ),
                player_ast = Assists(
                    id = this.home.leaders.assists[0].reference,
                    name = this.home.leaders.assists[0].full_name,
                    amount = this.home.leaders.assists[0].statistics.assists.toString()
                ),
                team_name = this.home.alias
            ),
            GameLeaders(
                game_id = this.id,
                player_pts = Points(
                    id = this.away.leaders.points[0].reference,
                    name = this.away.leaders.points[0].full_name,
                    amount = this.away.leaders.points[0].statistics.points.toString()
                ),
                player_reb = Rebounds(
                    id = this.away.leaders.rebounds[0].reference,
                    name = this.away.leaders.rebounds[0].full_name,
                    amount = this.away.leaders.rebounds[0].statistics.rebounds.toString()
                ),
                player_ast = Assists(
                    id = this.away.leaders.assists[0].reference,
                    name = this.away.leaders.assists[0].full_name,
                    amount = this.away.leaders.assists[0].statistics.assists.toString()
                ),
                team_name = this.away.alias
            )
        )
    }