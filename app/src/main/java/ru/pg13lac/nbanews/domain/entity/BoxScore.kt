package ru.pg13lac.nbanews.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BoxScore(
    var team_name: String?,
    var player_name: String?,
    var player_surname: String?,
    var player_pts: String?,
    var player_id: String,
    var player_reb: String?,
    var player_ast: String?
) : Parcelable