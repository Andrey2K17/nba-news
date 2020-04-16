package ru.pg13lac.nbanews.presentation.ui.team_profile

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_team_profile.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.common.TeamNameWithId
import ru.pg13lac.nbanews.common.setTeamImage
import ru.pg13lac.nbanews.domain.entity.OnClickPlayerCallback
import ru.pg13lac.nbanews.domain.entity.teamProfile.Player
import ru.pg13lac.nbanews.presentation.ui.base.BaseFragment
import ru.pg13lac.nbanews.presentation.viewModel.team_profile.TeamProfileViewModel
import javax.inject.Inject

class TeamProfileFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_team_profile

    @Inject
    lateinit var viewModel: TeamProfileViewModel

    private val disposeBag = CompositeDisposable()

    @Inject
    lateinit var rosterAdapter: RosterAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val team = arguments?.getParcelable<TeamNameWithId>("team")

        team?.let { setTeamImage(it.ref, ivTeamProfile) }

        team?.let {
            viewModel.getTeamProfile(it.id)
        }

        rvRoster.layoutManager = LinearLayoutManager(activity)
        rvRoster.adapter = rosterAdapter
        rosterAdapter.attachCallback(object : OnClickPlayerCallback {
            override fun routeTo(player: Player) {
                routeToPlayerInfo(player)
            }
        })
    }

    fun routeToPlayerInfo(player: Player) {
        val bundle = Bundle()
        bundle.putParcelable("player", player)
        findNavController().navigate(R.id.actionToPlayerProfileFragment, bundle)
    }

    override fun setModelBindings() {
        viewModel.teamProfile
            .subscribe {
                it?.name?.let { name -> tvTeamName.text = name }
                tvFoundedYear.text = it.founded.toString()
                rosterAdapter.mDataList = it.players
            }
            .addTo(disposeBag)
    }
}
