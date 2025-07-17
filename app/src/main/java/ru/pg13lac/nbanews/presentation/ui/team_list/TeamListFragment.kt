package ru.pg13lac.nbanews.presentation.ui.team_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.common.TeamNameWithId
import ru.pg13lac.nbanews.common.teamList
import ru.pg13lac.nbanews.databinding.FragmentTeamListBinding
import ru.pg13lac.nbanews.domain.entity.OnClickTeamCallback
import ru.pg13lac.nbanews.presentation.ui.base.BaseFragment
import javax.inject.Inject

class TeamListFragment : BaseFragment<FragmentTeamListBinding>(R.layout.fragment_team_list) {

    @Inject
    lateinit var teamListAdapter: TeamListAdapter
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentTeamListBinding {
        return FragmentTeamListBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvTeamList.layoutManager = LinearLayoutManager(activity)
        teamListAdapter.mDataList = teamList
        teamListAdapter.attachCallback(object : OnClickTeamCallback {
            override fun routeTo(team: TeamNameWithId) {
                routeToTeamProfile(team)
            }
        })
        binding.rvTeamList.adapter = teamListAdapter
    }

    fun routeToTeamProfile(team: TeamNameWithId) {
        val bundle = Bundle()
        bundle.putParcelable("team", team)
        findNavController().navigate(R.id.actionToTeamProfileFragment, bundle)
    }
}
