package ru.pg13lac.nbanews.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamPointsForQuarter(
    val left_team_q1: String? = null,
    val left_team_q2: String? = null,
    val left_team_q3: String? = null,
    val left_team_q4: String? = null,
    val left_team_tot: String? = null,
    val right_team_q1: String? = null,
    val right_team_q2: String? = null,
    val right_team_q3: String? = null,
    val right_team_q4: String? = null,
    val right_team_tot: String? = null,
    val game_id: String? = null,
    val leftTeam: String? = null,
    val rightTeam: String? = null
) : Parcelable