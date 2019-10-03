package ru.pg13lac.nbanews.domain.entity

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

sealed class GameLeadersType : GameLeadersTypeStats
@Parcelize
data class Points(
    override val id: String, override val name: String, override val amount: String
) : Parcelable, GameLeadersType()

@Parcelize
data class Rebounds(
    override val id: String, override val name: String, override val amount: String
) : Parcelable, GameLeadersType()

@Parcelize
data class Assists(
    override val id: String, override val name: String, override val amount: String
) : Parcelable, GameLeadersType()