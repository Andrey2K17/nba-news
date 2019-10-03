package ru.pg13lac.nbanews.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameLeaders(
    val game_id: String,
    val player_pts: Points,
    val player_reb: Rebounds,
    val player_ast: Assists,
    val team_name: String
) : Parcelable