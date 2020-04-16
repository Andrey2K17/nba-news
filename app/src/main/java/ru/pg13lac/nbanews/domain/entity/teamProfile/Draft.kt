package ru.pg13lac.nbanews.domain.entity.teamProfile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Draft(
    val pick: String,
    val round: String,
    val team_id: String,
    val year: Int
) : Parcelable