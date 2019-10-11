package ru.pg13lac.nbanews.domain.entity

import android.os.Parcelable
import io.reactivex.subjects.BehaviorSubject
import kotlinx.android.parcel.Parcelize
import kotlinx.android.parcel.RawValue

@Parcelize
data class Summary(
    val teamPointsForQuarter: TeamPointsForQuarter?,
    val gameLeaders: List<GameLeaders>?,
    val gameDetails: GameDetails?
) : Parcelable