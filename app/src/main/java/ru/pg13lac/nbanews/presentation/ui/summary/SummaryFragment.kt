package ru.pg13lac.nbanews.presentation.ui.summary

import android.os.Bundle
import android.view.View
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_summary.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.domain.entity.CommonTypeStats
import ru.pg13lac.nbanews.presentation.ui.base.BaseFragment
import ru.pg13lac.nbanews.presentation.viewModel.summary_game_details.SummaryGameDetailsViewModel
import javax.inject.Inject

class SummaryFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_summary

    @Inject
    lateinit var viewModel: SummaryGameDetailsViewModel

    private val disposeBag = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gameId = arguments?.getString("gameId")
        gameId?.let { viewModel.getGamesDetails(it) }
    }

    override fun setModelBindings() {
        super.setModelBindings()
        viewModel.gameDetails
            .subscribe {
                tcSummary.setTeamComparisonView(it)
            }
            .addTo(disposeBag)

        viewModel.teamPointsForQuarter
            .subscribe {
                qtSummary.setQuarterTableView(it)
            }
            .addTo(disposeBag)

        viewModel.gameLeaders
            .subscribe {
                it?.let {
                    glSummaryPoints.setGameLeadersInView(it, CommonTypeStats.POINT)
                    glSummaryRebounds.setGameLeadersInView(it, CommonTypeStats.REBOUND)
                    glSummaryAssists.setGameLeadersInView(it, CommonTypeStats.ASSIST)
                }
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
