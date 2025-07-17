package ru.pg13lac.nbanews.presentation.components

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.domain.entity.Pair
import ru.pg13lac.nbanews.domain.entity.Shots

class ComparisonLine @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    // View binding вместо синтетического views
    private val tvLeftTeamMadeStatsIndicator by lazy { findViewById<TextView>(R.id.tvLeftTeamMadeStatsIndicator) }
    private val tvLeftTeamThrowsAttempt by lazy { findViewById<TextView>(R.id.tvLeftTeamThrowsAttempt) }
    private val tvLeftTeamThrowsPercentage by lazy { findViewById<TextView>(R.id.tvLeftTeamThrowsPercentage) }
    private val tvRightTeamMadeStatsIndicator by lazy { findViewById<TextView>(R.id.tvRightTeamMadeStatsIndicator) }
    private val tvRightTeamThrowsAttempt by lazy { findViewById<TextView>(R.id.tvRightTeamThrowsAttempt) }
    private val tvRightTeamThrowsPercentage by lazy { findViewById<TextView>(R.id.tvRightTeamThrowsPercentage) }
    private val tvNameComparison by lazy { findViewById<TextView>(R.id.tvNameComparison) }
    private val clComparisonLine by lazy { findViewById<ConstraintLayout>(R.id.clComparisonLine) }
    private val vLeftLine by lazy { findViewById<View>(R.id.vLeftLine) }
    private val vRightLine by lazy { findViewById<View>(R.id.vRightLine) }

    init {
        LayoutInflater.from(context).inflate(R.layout.comparison_line, this, true)
    }

    @SuppressLint("SetTextI18n")
    fun setView(pair: Pair<Shots, Shots>, nameComparison: String) {
        pair.first.throwsMade?.let { tvLeftTeamMadeStatsIndicator.text = it.toString() }
        pair.first.throwsAttempted?.let { tvLeftTeamThrowsAttempt.text = "/$it" }
        pair.first.throwsPercentage?.let {
            tvLeftTeamThrowsPercentage.text = "($it%)"
        }

        pair.second.throwsMade?.let { tvRightTeamMadeStatsIndicator.text = it.toString() }
        pair.second.throwsAttempted?.let { tvRightTeamThrowsAttempt.text = "/$it" }
        pair.second.throwsPercentage?.let {
            tvRightTeamThrowsPercentage.text = "($it%)"
        }

        if (pair.first.throwsMade != null && pair.second.throwsMade != null) {
            if (!pair.first.throwsPercentage.isNullOrBlank()) {
                setLine(
                    (pair.first.throwsPercentage.toFloat() / 10).toString(),
                    pair.second.throwsPercentage?.toFloat()?.div(10)?.toString() ?: "0"
                )
            } else {
                setLine(
                    pair.first.throwsMade.toString(),
                    pair.second.throwsMade.toString()
                )
            }
        }
        tvNameComparison.text = nameComparison
    }

    private fun setLine(fgLeftTeam: String, fgRightTeam: String) {
        val constraintSet = ConstraintSet()
        constraintSet.clone(clComparisonLine)
        val (leftWeight, rightWeight) = convertTeamStats(fgLeftTeam, fgRightTeam)
        constraintSet.setHorizontalWeight(vLeftLine.id, leftWeight)
        constraintSet.setHorizontalWeight(vRightLine.id, rightWeight)
        constraintSet.applyTo(clComparisonLine)
    }

    private fun convertTeamStats(leftTeam: String, rightTeam: String): Pair<Float, Float> {
        val left = leftTeam.toFloatOrNull() ?: 0f
        val right = rightTeam.toFloatOrNull() ?: 0f
        val total = left + right
        val onePercent = if (total > 0) total / 100 else 1f
        return Pair(left / onePercent, right / onePercent)
    }
}