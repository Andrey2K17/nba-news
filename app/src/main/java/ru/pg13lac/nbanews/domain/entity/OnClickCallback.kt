package ru.pg13lac.nbanews.domain.entity

interface OnClickCallback {
    fun routeTo(gameId: String, vTeam: String, hTeam: String)
}