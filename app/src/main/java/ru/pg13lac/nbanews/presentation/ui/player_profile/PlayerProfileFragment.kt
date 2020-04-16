package ru.pg13lac.nbanews.presentation.ui.player_profile

import android.os.Bundle
import android.view.View
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_player_profile.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.common.setPlayerImage
import ru.pg13lac.nbanews.domain.entity.teamProfile.Player
import ru.pg13lac.nbanews.presentation.ui.base.BaseFragment
import ru.pg13lac.nbanews.presentation.viewModel.player_profile.PlayerProfileViewModel
import javax.inject.Inject


class PlayerProfileFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_player_profile

    @Inject
    lateinit var viewModel: PlayerProfileViewModel

    private val disposeBag = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val player = arguments?.getParcelable<Player>("player")
        player?.let {
            viewModel.getPlayerProfile(it.id)
        }
    }

    override fun setModelBindings() {
        viewModel.playerProfile
            .subscribe {
                setPlayerImage(it.reference, ivPlayerProfile)
                tvPlayerName.text = it.full_name
                tvPlayerProfileCommonInfo.text =
                    "${it.team.alias} | #${it.jersey_number} | ${it.position}"
                it.seasons[0]?.let { season -> firstSeason.setStats(season) }
                it.seasons[1]?.let { season -> secondSeason.setStats(season) }
                it.seasons[2]?.let { season -> thirdSeason.setStats(season) }
                it.seasons[3]?.let { season -> fourthSeason.setStats(season) }
            }
            .addTo(disposeBag)
    }

}
