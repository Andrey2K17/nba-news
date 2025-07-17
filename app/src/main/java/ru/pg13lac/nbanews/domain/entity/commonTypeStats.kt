package ru.pg13lac.nbanews.domain.entity

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.parcelize.RawValue

enum class CommonTypeStats {
    POINT, REBOUND, ASSIST
}

@Parcelize
data class Pair<T, P>(val first: @RawValue T, val second: @RawValue P) : Parcelable