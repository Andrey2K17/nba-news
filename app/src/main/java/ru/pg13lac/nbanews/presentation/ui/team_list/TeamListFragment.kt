package ru.pg13lac.nbanews.presentation.ui.team_list

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_team_list.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.common.teamList
import ru.pg13lac.nbanews.presentation.ui.base.BaseFragment
import javax.inject.Inject

class TeamListFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_team_list

    @Inject
    lateinit var teamListAdapter: TeamListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvTeamList.layoutManager = LinearLayoutManager(activity)
        teamListAdapter.mDataList = teamList
        rvTeamList.adapter = teamListAdapter
    }
}
