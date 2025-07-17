package ru.pg13lac.nbanews.presentation.ui.player_profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.common.setPlayerImage
import ru.pg13lac.nbanews.databinding.FragmentPlayerProfileBinding
import ru.pg13lac.nbanews.domain.entity.teamProfile.Player
import ru.pg13lac.nbanews.presentation.ui.base.BaseFragment
import ru.pg13lac.nbanews.presentation.viewModel.player_profile.PlayerProfileViewModel
import javax.inject.Inject


class PlayerProfileFragment : BaseFragment<FragmentPlayerProfileBinding>(R.layout.fragment_player_profile) {
    @Inject
    lateinit var viewModel: PlayerProfileViewModel

    private val disposeBag = CompositeDisposable()
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentPlayerProfileBinding {
        return FragmentPlayerProfileBinding.inflate(inflater, container, false)
    }

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
                setPlayerImage(it.reference, binding.ivPlayerProfile)
                binding.tvPlayerName.text = it.full_name
                binding.tvPlayerProfileCommonInfo.text =
                    "${it.team.alias} | #${it.jersey_number} | ${it.position}"
                it.seasons[0]?.let { season -> binding.firstSeason.setStats(season) }
                it.seasons[1]?.let { season -> binding.secondSeason.setStats(season) }
                it.seasons[2]?.let { season -> binding.thirdSeason.setStats(season) }
                it.seasons[3]?.let { season -> binding.fourthSeason.setStats(season) }
            }
            .addTo(disposeBag)
    }

}
