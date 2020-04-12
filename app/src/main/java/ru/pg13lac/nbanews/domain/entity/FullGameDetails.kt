package ru.pg13lac.nbanews.domain.entity

import ru.pg13lac.nbanews.domain.entity.gameLeaders.GameBoxScoreLeaders
import ru.pg13lac.nbanews.domain.entity.summaryGameDetails.SummaryGameDetails

data class FullGameDetails(
    val summaryGameDetails: SummaryGameDetails,
    val gameBoxScoreLeaders: GameBoxScoreLeaders
)