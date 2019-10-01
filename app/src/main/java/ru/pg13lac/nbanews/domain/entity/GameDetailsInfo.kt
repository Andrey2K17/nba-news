package ru.pg13lac.nbanews.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class GameDetailsInfo(
    val gameId: String,
    val leftTeam: String,
    val rightTeam: String
) : Parcelable