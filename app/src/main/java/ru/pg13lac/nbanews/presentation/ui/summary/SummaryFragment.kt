package ru.pg13lac.nbanews.presentation.ui.summary

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_summary.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.domain.entity.CommonTypeStats
import ru.pg13lac.nbanews.domain.entity.Summary
import ru.pg13lac.nbanews.presentation.ui.base.BaseFragment
import ru.pg13lac.nbanews.presentation.viewModel.summary.GameDetailsViewModel
import javax.inject.Inject

class SummaryFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_summary

    @Inject
    lateinit var viewModel: GameDetailsViewModel

    private val disposable = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val summary = arguments?.get("summary") as Summary

        qtSummary.setQuarterTableView(summary)

        summary.gameLeaders?.let {
            glSummaryPoints.setGameLeadersInView(it, CommonTypeStats.POINT)
            glSummaryRebounds.setGameLeadersInView(it, CommonTypeStats.REBOUND)
            glSummaryAssists.setGameLeadersInView(it, CommonTypeStats.ASSIST)
        }
    }

    override fun setModelBindings() {
        super.setModelBindings()
        viewModel.gameDetails
            .subscribe { tcSummary.setTeamComparisonView(it) }
            .addTo(disposable)
    }

    companion object {
        fun newInstance(summary: Summary): SummaryFragment {
            val fragment = SummaryFragment()
            val bundle = bundleOf("summary" to summary)
            fragment.arguments = bundle
            return fragment
        }
    }
}
