package ru.pg13lac.nbanews.presentation.components

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import kotlinx.android.synthetic.main.comparison_line.view.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.domain.entity.Shots

class ComparisonLine @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {
    init {
        LayoutInflater.from(context).inflate(R.layout.comparison_line, this, true)
    }

    @SuppressLint("SetTextI18n")
    fun setView(pair: Pair<Shots, Shots>, nameComparison: String) {
        tvLeftTeamMadeStatsIndicator.text = pair.first.throwsMade

        pair.first.throwsAttempted?.let { tvLeftTeamThrowsAttempt.text = "/$it" }

        pair.first.throwsPercentage?.let {
            tvLeftTeamThrowsPercentage.text = "(" + it.drop(2).dropLast(1) + "%)"
        }

        tvRightTeamMadeStatsIndicator.text = pair.second.throwsMade

        pair.second.throwsAttempted?.let { tvRightTeamThrowsAttempt.text = "/$it" }

        pair.second.throwsPercentage?.let {
            tvRightTeamThrowsPercentage.text = "(" + it.drop(2).dropLast(1) + "%)"
        }

        if (pair.first.throwsMade !== null && pair.second.throwsMade !== null) {
            if (!pair.first.throwsPercentage.isNullOrBlank()) {
                setLine(
                    (pair.first.throwsPercentage!!.drop(2).toFloat() / 10).toString(),
                    (pair.second.throwsPercentage!!.drop(2).toFloat() / 10).toString()
                )
            } else {
                setLine(pair.first.throwsMade!!, pair.second.throwsMade!!)
            }

        }
        tvNameComparison.text = nameComparison
    }

    private fun setLine(
        fgLeftTeam: String,
        fgRightTeam: String
    ) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(clComparisonLine)
        constraintSet.setHorizontalWeight(
            R.id.vLeftLine,
            convertTeamStats(fgLeftTeam, fgRightTeam).first
        )
        constraintSet.setHorizontalWeight(
            R.id.vRightLine,
            convertTeamStats(fgLeftTeam, fgRightTeam).second
        )
        constraintSet.applyTo(clComparisonLine)
    }

    private fun convertTeamStats(leftTeam: String, rightTeam: String): Pair<Float, Float> {
        val onePercent =
            (leftTeam.toFloat() + rightTeam.toFloat()) / 100
        return Pair(leftTeam.toFloat() / onePercent, rightTeam.toFloat() / onePercent)
    }
}