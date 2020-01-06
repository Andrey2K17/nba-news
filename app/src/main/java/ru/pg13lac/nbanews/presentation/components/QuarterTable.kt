package ru.pg13lac.nbanews.presentation.components

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import androidx.constraintlayout.widget.ConstraintLayout
import kotlinx.android.synthetic.main.quarter_table.view.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.domain.entity.TeamPointsForQuarter

class QuarterTable @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    init {
        LayoutInflater.from(context).inflate(R.layout.quarter_table, this, true)
    }

    fun setQuarterTableView(teamPointsForQuarter: TeamPointsForQuarter) {
        tvLeftTeamQ1.text = teamPointsForQuarter.left_team_q1
        tvLeftTeamQ2.text = teamPointsForQuarter.left_team_q2
        tvLeftTeamQ3.text = teamPointsForQuarter.left_team_q3
        tvLeftTeamQ4.text = teamPointsForQuarter.left_team_q4
        tvLeftTeamTOT.text = teamPointsForQuarter.left_team_tot
        tvRightTeamQ1.text = teamPointsForQuarter.right_team_q1
        tvRightTeamQ2.text = teamPointsForQuarter.right_team_q2
        tvRightTeamQ3.text = teamPointsForQuarter.right_team_q3
        tvRightTeamQ4.text = teamPointsForQuarter.right_team_q4
        tvRightTeamTOT.text = teamPointsForQuarter.right_team_tot
        tvLeftTeam.text = teamPointsForQuarter.leftTeam
        tvRightTeam.text = teamPointsForQuarter.rightTeam
    }
}
