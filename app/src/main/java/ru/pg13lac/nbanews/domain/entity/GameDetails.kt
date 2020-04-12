package ru.pg13lac.nbanews.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameDetails(
    val boxScore: Pair<MutableList<BoxScore>, MutableList<BoxScore>>? = null,
    val teamComparison: Pair<TeamComparison, TeamComparison>? = null,
    val gameDetailsInfo: GameDetailsInfo? = null
) : Parcelable