package ru.pg13lac.nbanews.domain.entity.testCompleteStats

import ru.pg13lac.nbanews.domain.entity.testGameDetails.TestGameDetails
import ru.pg13lac.nbanews.domain.entity.testGameStatistic.TestGameStatistic

data class TestCompleteStats(
    val testGameDetails: TestGameDetails,
    val testGameStatistic: TestGameStatistic
)