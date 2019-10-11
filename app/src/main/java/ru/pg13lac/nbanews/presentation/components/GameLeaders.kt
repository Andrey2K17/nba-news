package ru.pg13lac.nbanews.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.game_leaders.view.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.common.setPlayerImage
import ru.pg13lac.nbanews.domain.entity.CommonTypeStats
import ru.pg13lac.nbanews.domain.entity.GameLeaders
import ru.pg13lac.nbanews.domain.entity.GameLeadersType

class GameLeaders @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    init {
        LayoutInflater.from(context).inflate(R.layout.game_leaders, this, true)
    }

    fun setGameLeadersInView(gameLeaders: List<GameLeaders>, type: CommonTypeStats) {
        val playerStats = mutableListOf<GameLeadersType>()
        var statsCategory = ""
        if (gameLeaders.isNotEmpty()) {
            when (type) {
                CommonTypeStats.POINT -> {
                    for (i in 0..1) {
                        playerStats.add(gameLeaders[i].player_pts)
                    }
                    statsCategory = context.getString(R.string.point_category)
                }
                CommonTypeStats.REBOUND -> {
                    for (i in 0..1) {
                        playerStats.add(gameLeaders[i].player_reb)
                    }
                    statsCategory = context.getString(R.string.rebound_category)
                }
                CommonTypeStats.ASSIST -> {
                    for (i in 0..1) {
                        playerStats.add(gameLeaders[i].player_ast)
                    }
                    statsCategory = context.getString(R.string.assist_category)
                }
            }
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
}