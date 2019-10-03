package ru.pg13lac.nbanews.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamPointsForQuarter(
    val left_team_q1: String,
    val left_team_q2: String,
    val left_team_q3: String,
    val left_team_q4: String,
    val left_team_tot: String,
    val right_team_q1: String,
    val right_team_q2: String,
    val right_team_q3: String,
    val right_team_q4: String,
    val right_team_tot: String,
    val game_id: String,
    val leftTeam: String,
    val rightTeam: String
) : Parcelable