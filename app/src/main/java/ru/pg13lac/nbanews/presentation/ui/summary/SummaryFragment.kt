package ru.pg13lac.nbanews.presentation.ui.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.databinding.FragmentSummaryBinding
import ru.pg13lac.nbanews.domain.entity.CommonTypeStats
import ru.pg13lac.nbanews.presentation.ui.base.BaseFragment
import ru.pg13lac.nbanews.presentation.viewModel.summary_game_details.SummaryGameDetailsViewModel
import javax.inject.Inject

class SummaryFragment : BaseFragment<FragmentSummaryBinding>(R.layout.fragment_summary) {

    @Inject
    lateinit var viewModel: SummaryGameDetailsViewModel

    private val disposeBag = CompositeDisposable()

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSummaryBinding {
        return FragmentSummaryBinding.inflate(inflater, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gameId = arguments?.getString("gameId")
        gameId?.let { viewModel.getSummaryGameDetails(it) }
    }

    override fun setModelBindings() {
        super.setModelBindings()
        viewModel.gameDetails
            .subscribe {
                binding.tcSummary.setTeamComparisonView(it)
            }
            .addTo(disposeBag)

        viewModel.teamPointsForQuarter
            .subscribe {
                binding.qtSummary.setQuarterTableView(it)
            }
            .addTo(disposeBag)

        viewModel.gameLeaders
            .subscribe {
                it?.let {
                    binding.glSummaryPoints.setGameLeadersInView(it, CommonTypeStats.POINT)
                    binding.glSummaryRebounds.setGameLeadersInView(it, CommonTypeStats.REBOUND)
                    binding.glSummaryAssists.setGameLeadersInView(it, CommonTypeStats.ASSIST)
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
