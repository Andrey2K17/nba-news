package ru.pg13lac.nbanews.presentation.ui.game_details

import android.os.Bundle
import android.view.View
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_game_details.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.domain.entity.GameDetails
import ru.pg13lac.nbanews.presentation.ui.base.BaseFragment
import ru.pg13lac.nbanews.presentation.ui.box_score.BoxScoreFragment
import ru.pg13lac.nbanews.presentation.ui.summary.SummaryFragment
import ru.pg13lac.nbanews.presentation.viewModel.game_details.GameDetailsViewModel
import javax.inject.Inject

class GameDetailsFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_game_details

    @Inject
    lateinit var viewModel: GameDetailsViewModel

    private var gameDetails: GameDetails? = null

    private val disposeBag = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gameId = arguments?.getString("gameId")
        val date = arguments?.getString("date")
        viewModel.getGameDetails(date!!, gameId!!)

        val adapter = GameDetailsViewPagerAdapter(childFragmentManager)

        adapter.addFragment(SummaryFragment(), getString(R.string.summary))
        adapter.addFragment(BoxScoreFragment(), getString(R.string.box_score))

        vpGameDetails.adapter = adapter
        tlGameDetails.setupWithViewPager(vpGameDetails)
    }

    override fun setModelBindings() {
        super.setModelBindings()
        viewModel.gameDetails
            .subscribe {
                gameDetails = it
            }
            .addTo(disposeBag)

        viewModel.isError
            .filter { it }
            .subscribe {
                showMessage(R.string.error_data_loading)
            }
            .addTo(disposeBag)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        disposeBag.clear()
    }
}
