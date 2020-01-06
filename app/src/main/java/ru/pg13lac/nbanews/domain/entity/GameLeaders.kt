package ru.pg13lac.nbanews.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameLeasders(
    val game_id: String? = null,
    val player_pts: Points? = null,
    val player_reb: Rebounds? = null,
    val player_ast: Assists? = null,
    val team_name: String? = null
) : Parcelable