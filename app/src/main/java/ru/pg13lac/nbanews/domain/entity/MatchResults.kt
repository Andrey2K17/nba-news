package ru.pg13lac.nbanews.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class MatchResults(
    val gameScoreList: @RawValue List<GameItem>? = null,
    val teamPointsForQuarterList: List<TeamPointsForQuarter>,
    val gameLeaders: List<GameLeaders>
) : Parcelable