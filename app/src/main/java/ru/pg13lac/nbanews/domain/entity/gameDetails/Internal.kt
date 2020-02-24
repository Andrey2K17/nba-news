package ru.pg13lac.nbanews.domain.entity.gameDetails

data class Internal(
    val consolidatedDomKey: String,
    val endToEndTimeMillis: String,
    val igorPath: String,
    val pubDateTime: String,
    val routeName: String,
    val routeValue: String,
    val xslt: String,
    val xsltCompileTimeMillis: String,
    val xsltForceRecompile: String,
    val xsltInCache: String,
    val xsltTransformTimeMillis: String
)