package ru.pg13lac.nbanews.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TeamComparison(
    val TEAM_ABBREVIATION: String?,
    val FGM: String?,
    val FGA: String?,
    val FG_PCT: String?,
    val FG3M: String?,
    val FG3A: String?,
    val FG3_PCT: String?,
    val FTM: String?,
    val FTA: String?,
    val FT_PCT: String?,
    val AST: String?,
    val REB: String?,
    val O_REB: String?,
    val D_REB: String?,
    val STL: String?,
    val BLK: String?,
    val TO: String?
) : Parcelable