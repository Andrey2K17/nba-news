package ru.pg13lac.nbanews.presentation.ui.summary


import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import kotlinx.android.synthetic.main.fragment_summary.*
import kotlinx.android.synthetic.main.quarter_table.view.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.domain.entity.TeamPointsForQuarter
import ru.pg13lac.nbanews.presentation.ui.base.BaseFragment

class SummaryFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_summary

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gameInfo = arguments?.getParcelable("gameInfo") as TeamPointsForQuarter

        with(qtSummary) {
            tvLeftTeamQ1.text = gameInfo.left_team_q1
            tvLeftTeamQ2.text = gameInfo.left_team_q2
            tvLeftTeamQ3.text = gameInfo.left_team_q3
            tvLeftTeamQ4.text = gameInfo.left_team_q4
            tvLeftTeamTOT.text = gameInfo.left_team_tot
            tvRightTeamQ1.text = gameInfo.right_team_q1
            tvRightTeamQ2.text = gameInfo.right_team_q2
            tvRightTeamQ3.text = gameInfo.right_team_q3
            tvRightTeamQ4.text = gameInfo.right_team_q4
            tvRightTeamTOT.text = gameInfo.right_team_tot
            tvLeftTeam.text = gameInfo.leftTeam
            tvRightTeam.text = gameInfo.rightTeam
        }
    }

    companion object {
        fun newInstance(teamPointsForQuarter: TeamPointsForQuarter): SummaryFragment {
            val fragment = SummaryFragment()
            val bundle = bundleOf("gameInfo" to teamPointsForQuarter)
            fragment.arguments = bundle
            return fragment
        }
    }
}
