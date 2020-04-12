package ru.pg13lac.nbanews.domain.entity.gameLeaders

data class LeadersX(
    val assists: List<AssistX>,
    val points: List<PointX>,
    val rebounds: List<ReboundX>
)