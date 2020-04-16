package ru.pg13lac.nbanews.domain.entity.teamProfile

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Player(
    val abbr_name: String,
    val birth_place: String,
    val birthdate: String,
    val college: String,
    val draft: Draft,
    val experience: String,
    val first_name: String,
    val full_name: String,
    val height: Int,
    val high_school: String,
    val id: String,
    val injuries: List<Injury>,
    val jersey_number: String,
    val last_name: String,
    val name_suffix: String,
    val position: String,
    val primary_position: String,
    val reference: String,
    val rookie_year: Int,
    val sr_id: String,
    val status: String,
    val updated: String,
    val weight: Int
) : Parcelable