package ru.pg13lac.nbanews.domain.entity.gameDetails

data class Period(
    val current: Int,
    val isEndOfPeriod: Boolean,
    val isHalftime: Boolean,
    val maxRegular: Int,
    val type: Int
)