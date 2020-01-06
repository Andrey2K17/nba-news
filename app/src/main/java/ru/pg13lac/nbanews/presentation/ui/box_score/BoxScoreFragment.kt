package ru.pg13lac.nbanews.presentation.ui.box_score

import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_box_score.*
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.common.isVisible
import ru.pg13lac.nbanews.domain.entity.GameDetails
import ru.pg13lac.nbanews.presentation.ui.base.BaseFragment
import ru.pg13lac.nbanews.presentation.viewModel.game_details.GameDetailsViewModel
import javax.inject.Inject

class BoxScoreFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_box_score

    @Inject
    lateinit var viewModel: GameDetailsViewModel

    private val disposeBag = CompositeDisposable()
    @Inject
    lateinit var boxScoreAdapter: BoxScoreAdapter

    private var gameDetails: GameDetails? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        rvBoxScore.layoutManager = LinearLayoutManager(activity)
        rvBoxScore.adapter = boxScoreAdapter
    }

    override fun setListeners() {
        tlBoxScore.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {}

            override fun onTabUnselected(p0: TabLayout.Tab?) {}

            override fun onTabSelected(tab: TabLayout.Tab?) {
                when (tab?.position) {
                    0 -> boxScoreAdapter.mDataList =
                        gameDetails?.boxScore?.let { boxScore ->
                            boxScore.filter { it.team_name == boxScore[0].team_name }
                        } ?: emptyList()
                    1 -> boxScoreAdapter.mDataList =
                        gameDetails?.boxScore?.let { boxScore ->
                            boxScore.filter { it.team_name == boxScore.last().team_name }
                        } ?: emptyList()
                }
            }
        })
    }

    override fun setModelBindings() {
        super.setModelBindings()
        viewModel.gameDetails
            .subscribe { games ->
                gameDetails = games
                boxScoreAdapter.mDataList = games?.boxScore?.let { boxScore ->
                    boxScore.filter { it.team_name == boxScore[0].team_name }
                } ?: emptyList()
                tlBoxScore?.getTabAt(0)?.text = gameDetails?.gameDetailsInfo?.leftTeam
                tlBoxScore?.getTabAt(1)?.text = gameDetails?.gameDetailsInfo?.rightTeam
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
