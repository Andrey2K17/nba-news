package ru.pg13lac.nbanews.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.game_leaders.view.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.common.setPlayerImage
import ru.pg13lac.nbanews.domain.entity.*
import ru.pg13lac.nbanews.domain.entity.GameLeaders

class GameLeaders @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    init {
        LayoutInflater.from(context).inflate(R.layout.game_leaders, this, true)
    }

    fun setGameLeadersInView(gameLeaders: Pair<GameLeaders, GameLeaders>, type: CommonTypeStats) {
        var statsCategory = ""
        var playerLeftTypeStats: GameLeadersTypeStats? = null
        var playerRightTypeStats: GameLeadersTypeStats? = null
        when (type) {
            CommonTypeStats.POINT -> {
                playerLeftTypeStats = gameLeaders.first.player_pts
                playerRightTypeStats = gameLeaders.second.player_pts
                statsCategory = context.getString(R.string.point_category)
            }
            CommonTypeStats.REBOUND -> {
                playerLeftTypeStats = gameLeaders.first.player_reb
                playerRightTypeStats = gameLeaders.second.player_reb
                statsCategory = context.getString(R.string.rebound_category)
            }
            CommonTypeStats.ASSIST -> {
                playerLeftTypeStats = gameLeaders.first.player_ast
                playerRightTypeStats = gameLeaders.second.player_ast
                statsCategory = context.getString(R.string.assist_category)
            }
        }
        tvLeftPlayerName.text = playerLeftTypeStats?.name ?: ""
        tvRightPlayerName.text = playerRightTypeStats?.name ?: ""
        tvLeftPlayerAmount.text = playerLeftTypeStats?.amount ?: ""
        tvRightPlayerAmount.text = playerRightTypeStats?.amount ?: ""
        tvLeftTeam.text = gameLeaders.first.team_name
        tvRightTeam.text = gameLeaders.second.team_name
        setPlayerImage(playerLeftTypeStats?.id ?: "", ivLeftPlayer)
        setPlayerImage(playerRightTypeStats?.id ?: "", ivRightPlayer)
        tvStatsCategory.text = statsCategory
    }
}