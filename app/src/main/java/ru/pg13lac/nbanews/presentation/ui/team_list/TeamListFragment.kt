package ru.pg13lac.nbanews.presentation.ui.team_list

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_team_list.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.common.TeamNameWithId
import ru.pg13lac.nbanews.common.teamList
import ru.pg13lac.nbanews.domain.entity.OnClickCallback
import ru.pg13lac.nbanews.domain.entity.OnClickTeamCallback
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
        teamListAdapter.attachCallback(object : OnClickTeamCallback {
            override fun routeTo(team: TeamNameWithId) {
                routeToTeamProfile(team)
            }
        })
        rvTeamList.adapter = teamListAdapter
    }

    fun routeToTeamProfile(team: TeamNameWithId) {
        val bundle = Bundle()
        bundle.putParcelable("team", team)
        findNavController().navigate(R.id.actionToTeamProfileFragment, bundle)
    }
}
