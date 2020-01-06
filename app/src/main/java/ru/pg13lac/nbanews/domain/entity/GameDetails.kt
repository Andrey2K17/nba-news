package ru.pg13lac.nbanews.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameDetails(
    val boxScore: List<BoxScore>? = null,
    val teamComparison: Pair<TeamComparison?, TeamComparison?>? = null,
    val gameDetailsInfo: GameDetailsInfo? = null
) : Parcelable