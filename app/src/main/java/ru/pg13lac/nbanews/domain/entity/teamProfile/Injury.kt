package ru.pg13lac.nbanews.domain.entity.teamProfile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Injury(
    val comment: String,
    val desc: String,
    val id: String,
    val start_date: String,
    val status: String,
    val update_date: String
) : Parcelable