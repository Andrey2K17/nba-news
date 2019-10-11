package ru.pg13lac.nbanews.domain.entity

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameDetails(
    @SerializedName("boxScore") val boxScore: List<BoxScore>? = null,
    @SerializedName("teamComparison") val teamComparison: Pair<TeamComparison?, TeamComparison?>? = null,
    @SerializedName("gameDetailsInfo") val gameDetailsInfo: GameDetailsInfo? = null
) : Parcelable