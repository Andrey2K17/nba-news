package ru.pg13lac.nbanews.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.common.setPlayerImage
import ru.pg13lac.nbanews.domain.entity.CommonTypeStats
import ru.pg13lac.nbanews.domain.entity.GameLeaders
import ru.pg13lac.nbanews.domain.entity.GameLeadersTypeStats
import ru.pg13lac.nbanews.domain.entity.Pair

class GameLeaders @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    // View binding вместо синтетических view
    private val tvLeftPlayerName by lazy { findViewById<TextView>(R.id.tvLeftPlayerName) }
    private val tvRightPlayerName by lazy { findViewById<TextView>(R.id.tvRightPlayerName) }
    private val tvLeftPlayerAmount by lazy { findViewById<TextView>(R.id.tvLeftPlayerAmount) }
    private val tvRightPlayerAmount by lazy { findViewById<TextView>(R.id.tvRightPlayerAmount) }
    private val tvLeftTeam by lazy { findViewById<TextView>(R.id.tvLeftTeam) }
    private val tvRightTeam by lazy { findViewById<TextView>(R.id.tvRightTeam) }
    private val ivLeftPlayer by lazy { findViewById<ImageView>(R.id.ivLeftPlayer) }
    private val ivRightPlayer by lazy { findViewById<ImageView>(R.id.ivRightPlayer) }
    private val tvStatsCategory by lazy { findViewById<TextView>(R.id.tvStatsCategory) }

    init {
        LayoutInflater.from(context).inflate(R.layout.game_leaders, this, true)
    }

    fun setGameLeadersInView(gameLeaders: Pair<GameLeaders, GameLeaders>, type: CommonTypeStats) {
        val (leftLeader, rightLeader) = gameLeaders
        val (leftStats, rightStats) = when (type) {
            CommonTypeStats.POINT -> leftLeader.player_pts to rightLeader.player_pts
            CommonTypeStats.REBOUND -> leftLeader.player_reb to rightLeader.player_reb
            CommonTypeStats.ASSIST -> leftLeader.player_ast to rightLeader.player_ast
        }

        val statsCategoryRes = when (type) {
            CommonTypeStats.POINT -> R.string.point_category
            CommonTypeStats.REBOUND -> R.string.rebound_category
            CommonTypeStats.ASSIST -> R.string.assist_category
        }

        // Установка данных игроков
        requireNotNull(leftStats)
        with(leftStats) {
            tvLeftPlayerName.text = name.orEmpty()
            tvLeftPlayerAmount.text = amount.orEmpty()
            id?.let { setPlayerImage(it, ivLeftPlayer) }
        }
        requireNotNull(rightStats)
        with(rightStats) {
            tvRightPlayerName.text = name.orEmpty()
            tvRightPlayerAmount.text = amount.orEmpty()
            id?.let { setPlayerImage(it, ivRightPlayer) }
        }

        // Установка названий команд
        tvLeftTeam.text = leftLeader.team_name.orEmpty()
        tvRightTeam.text = rightLeader.team_name.orEmpty()

        // Установка категории статистики
        tvStatsCategory.text = context.getString(statsCategoryRes)
    }
}