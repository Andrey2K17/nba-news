package ru.pg13lac.nbanews.common

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.request.RequestOptions
import kotlinx.android.synthetic.main.game_leaders.view.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.domain.entity.GameLeaders
import ru.pg13lac.nbanews.domain.entity.CommonTypeStats
import ru.pg13lac.nbanews.domain.entity.GameLeadersType

fun ViewGroup.inflate(layoutRes: Int): View =
    LayoutInflater.from(context).inflate(layoutRes, this, false)

fun setPlayerImage(playerId: String, imageView: ImageView) {
    GlideApp.with(imageView.context)
        .load("https://ak-static.cms.nba.com/wp-content/uploads/headshots/nba/latest/260x190/${playerId}.png")
        .apply(RequestOptions.centerCropTransform())
        .placeholder(R.drawable.ic_person)
        .into(imageView)
}

fun setTeamImage(team: String, imageView: ImageView) {
    GlideApp.with(imageView.context)
        .load("https://stats.nba.com/media/img/teams/logos/${team}_logo.svg")
        .centerCrop()
        .into(imageView)
}

fun setGameLeadersInView(view: View, gameLeaders: List<GameLeaders>, type: CommonTypeStats) {
    val playerStats = mutableListOf<GameLeadersType>()
    var statsCategory = ""
    when (type) {
        CommonTypeStats.POINT -> {
            for (i in 0..1) {
                playerStats.add(gameLeaders[i].player_pts)
            }
            statsCategory = view.context.getString(R.string.point_category)
        }
        CommonTypeStats.REBOUND -> {
            for (i in 0..1) {
                playerStats.add(gameLeaders[i].player_reb)
            }
            statsCategory = view.context.getString(R.string.rebound_category)
        }
        CommonTypeStats.ASSIST -> {
            for (i in 0..1) {
                playerStats.add(gameLeaders[i].player_ast)
            }
            statsCategory = view.context.getString(R.string.assist_category)
        }
    }
    with(view) {
        tvLeftPlayerName.text = playerStats[1].name
        tvRightPlayerName.text = playerStats[0].name
        tvLeftPlayerAmount.text = playerStats[1].amount
        tvRightPlayerAmount.text = playerStats[0].amount
        tvLeftTeam.text = gameLeaders[1].team_name
        tvRightTeam.text = gameLeaders[0].team_name
        setPlayerImage(playerStats[1].id, ivLeftPlayer)
        setPlayerImage(playerStats[0].id, ivRightPlayer)
        tvStatsCategory.text = statsCategory
    }
}