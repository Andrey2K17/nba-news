package ru.pg13lac.nbanews.domain.entity

import ru.pg13lac.nbanews.common.TeamNameWithId
import ru.pg13lac.nbanews.domain.entity.teamProfile.Player

interface OnClickCallback {
    fun routeTo(gameId: String, vTeam: String, hTeam: String)
}

interface OnClickTeamCallback {
    fun routeTo(team: TeamNameWithId)
}

interface OnClickPlayerCallback {
    fun routeTo(player: Player)
}