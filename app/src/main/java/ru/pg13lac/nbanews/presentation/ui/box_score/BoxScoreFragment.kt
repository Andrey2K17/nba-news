package ru.pg13lac.nbanews.presentation.ui.box_score

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_box_score.*
import ru.pg13lac.nbanews.App
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.common.isVisible
import ru.pg13lac.nbanews.domain.entity.BoxScore
import ru.pg13lac.nbanews.domain.entity.GameDetails
import ru.pg13lac.nbanews.presentation.ui.base.BaseFragment
import ru.pg13lac.nbanews.presentation.viewModel.box_score.BoxScoreViewModel
import ru.pg13lac.nbanews.presentation.viewModel.summary_game_details.SummaryGameDetailsViewModel
import javax.inject.Inject

class BoxScoreFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_box_score

    @Inject
    lateinit var viewModel: BoxScoreViewModel

    private val disposeBag = CompositeDisposable()
    @Inject
    lateinit var boxScoreAdapter: BoxScoreAdapter

//    private var gameDetails: GameDetails? = null

    private var boxScore: List<BoxScore>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val gameId = arguments?.getString("gameId")
        val vTeam = arguments?.getString("vTeam")
        val hTeam = arguments?.getString("hTeam")


        tlBoxScore?.getTabAt(0)?.text = vTeam?: "VISITOR_TEAM"
        tlBoxScore?.getTabAt(1)?.text = hTeam?: "HOME_TEAM"
        gameId?.let { viewModel.getBoxScore(it) }

        rvBoxScore.layoutManager = LinearLayoutManager(activity)
        rvBoxScore.adapter = boxScoreAdapter
    }

    override fun setListeners() {
        tlBoxScore.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {}

            override fun onTabUnselected(p0: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> boxScoreAdapter.mDataList = boxScore?.let { boxScore ->
                            boxScore.filter { it.team_name == boxScore[0].team_name }
                        } ?: emptyList()
                    1 -> boxScoreAdapter.mDataList = boxScore?.let { boxScore ->
                            boxScore.filter { it.team_name == boxScore.last().team_name }
                        } ?: emptyList()
                }
            }
        })
    }

    override fun setModelBindings() {
        super.setModelBindings()
        viewModel.boxScore
            .subscribe { listOfBoxScore ->
                boxScore = listOfBoxScore
                listOfBoxScore?.let { boxScoreAdapter.mDataList =
                    it.filter { boxScore -> boxScore.team_name == it[0].team_name }
                }
            }
            .addTo(disposeBag)

        viewModel.isLoading
            .subscribe { isLoading ->
                clpbBoxScore?.isVisible = isLoading
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
