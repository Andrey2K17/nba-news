package ru.pg13lac.nbanews.domain.entity.dailyGames

import ru.pg13lac.nbanews.domain.entity.GameItem

fun DailyGames.toGameDetails(): MutableList<GameItem> {
    val scoreboardList = mutableListOf<GameItem>()
    for (i in this.games.indices) {
        scoreboardList.add(
            GameItem(
                left_img = this.games[i].home.alias,
                right_img = this.games[i].away.alias,
                left_team_name = this.games[i].home.name,
                right_team_name = this.games[i].away.name,
                left_team_pts = this.games[i].home_points.toString(),
                right_team_pts = this.games[i].away_points.toString(),
                game_status = this.games[i].status,
                gameId = this.games[i].id
            )
        )
    }
    return scoreboardList
}