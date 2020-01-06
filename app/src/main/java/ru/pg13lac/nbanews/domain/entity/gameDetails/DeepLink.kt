package ru.pg13lac.nbanews.domain.entity.gameDetails

data class DeepLink(
    val androidApp: String,
    val broadcaster: String,
    val desktopWeb: String,
    val iosApp: String,
    val mobileWeb: String,
    val regionalMarketCodes: String
)