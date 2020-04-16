package ru.pg13lac.nbanews.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.season_average_player.view.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.domain.entity.playerProfile.Season

class SeasonAveragePlayer @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    init {
        LayoutInflater.from(context).inflate(R.layout.season_average_player, this, true)
    }

    fun setStats(season: Season) {
        tvYearSeasonAverage.text = season.year.toString()
        tvPointSeasonAverage.text = season.teams[0].average.points.toString()
        tvReboundSeasonAverage.text = season.teams[0].average.rebounds.toString()
        tvAssistSeasonAverage.text = season.teams[0].average.assists.toString()
    }
}