package ru.pg13lac.nbanews.presentation.ui.box_score

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_box_score.*
import ru.pg13lac.nbanews.App
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.domain.entity.BoxScore
import ru.pg13lac.nbanews.domain.entity.GameDetailsInfo
import ru.pg13lac.nbanews.presentation.ui.base.BaseFragment
import ru.pg13lac.nbanews.presentation.viewModel.box_score.BoxScoreViewModel
import javax.inject.Inject

class BoxScoreFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_box_score

    @Inject
    lateinit var viewModel: BoxScoreViewModel

    private val disposeBag = CompositeDisposable()
    @Inject
    lateinit var boxScoreAdapter: BoxScoreAdapter

    private var boxScore = listOf<BoxScore>()

    private var gameInfo: GameDetailsInfo? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.instance.component.inject(this)
        gameInfo = arguments?.getParcelable("game") as GameDetailsInfo

        tlBoxScore.getTabAt(0)?.text = gameInfo?.leftTeam
        tlBoxScore.getTabAt(1)?.text = gameInfo?.rightTeam

        rvBoxScore.layoutManager = LinearLayoutManager(activity)
        rvBoxScore.adapter = boxScoreAdapter

        gameInfo?.let { viewModel.getBoxScore(it.gameId) }
    }

    override fun setListeners() {
        tlBoxScore.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {}

            override fun onTabUnselected(p0: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> boxScoreAdapter.mDataList =
                        boxScore.filter { it.team_name == boxScore[0].team_name }
                    1 -> boxScoreAdapter.mDataList =
                        boxScore.filter { it.team_name == boxScore.last().team_name }
                }
            }
        })

        srlBoxScore.setOnRefreshListener {
            gameInfo?.let { viewModel.getBoxScore(it.gameId) }
        }
    }

    override fun setModelBindings() {
        viewModel.boxScore
            .subscribe {
                boxScore = it
                boxScoreAdapter.mDataList = it.filter { boxScore ->
                    when (tlBoxScore.selectedTabPosition) {
                        0 -> boxScore.team_name == this.boxScore[0].team_name
                        1 -> boxScore.team_name == this.boxScore.last().team_name
                        else -> false
                    }

                }
            }
            .addTo(disposeBag)

        viewModel.isLoading
            .subscribe { isLoading ->
                srlBoxScore.isRefreshing = isLoading
            }
            .addTo(disposeBag)

        viewModel.isError
            .filter { it }
            .subscribe {
                showMessage(R.string.error_data_loading)
            }
            .addTo(disposeBag)
    }

    companion object {
        fun newInstance(gameInfo: GameDetailsInfo): BoxScoreFragment {
            val fragment = BoxScoreFragment()
            val bundle = bundleOf("game" to gameInfo)
            fragment.arguments = bundle
            return fragment
        }
    }
}
