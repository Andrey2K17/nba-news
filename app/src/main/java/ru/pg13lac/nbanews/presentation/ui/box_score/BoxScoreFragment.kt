package ru.pg13lac.nbanews.presentation.ui.box_score

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.core.os.bundleOf
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.tabs.TabLayout
import io.reactivex.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.fragment_box_score.*
import ru.pg13lac.nbanews.App
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.domain.entity.GameDetails
import ru.pg13lac.nbanews.presentation.ui.base.BaseFragment
import javax.inject.Inject

class BoxScoreFragment : BaseFragment() {
    override val layoutRes: Int = R.layout.fragment_box_score

//    @Inject
//    lateinit var viewModel: BoxScoreViewModel

    private val disposeBag = CompositeDisposable()
    @Inject
    lateinit var boxScoreAdapter: BoxScoreAdapter

    //private var boxScore = listOf<BoxScore>()

    private var gameDetails: GameDetails? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        gameDetails = arguments?.getParcelable("gameDetails") as GameDetails?

        tlBoxScore.getTabAt(0)?.text = gameDetails?.gameDetailsInfo?.leftTeam
        tlBoxScore.getTabAt(1)?.text = gameDetails?.gameDetailsInfo?.rightTeam

        rvBoxScore.layoutManager = LinearLayoutManager(activity)
        rvBoxScore.adapter = boxScoreAdapter

        // gameDetails?.let { viewModel.getGameDetails(it.gameId) }
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
//                        gameDetails?.boxScore?.filter { it.team_name == gameDetails?.boxScore?.last()?.team_name }
                }
            }
        })

        srlBoxScore.setOnRefreshListener {
            //gameDetails?.let { viewModel.getGameDetails(it.gameId) }
        }
    }

//    override fun setModelBindings() {
//        viewModel.boxScore
//            .subscribe {
//                boxScore = it
//                boxScoreAdapter.mDataList = it.filter { boxScore ->
//                    when (tlBoxScore.selectedTabPosition) {
//                        0 -> boxScore.team_name == this.boxScore[0].team_name
//                        1 -> boxScore.team_name == this.boxScore.last().team_name
//                        else -> false
//                    }
//
//                }
//            }
//            .addTo(disposeBag)
//
//        viewModel.isLoading
//            .subscribe { isLoading ->
//                srlBoxScore.isRefreshing = isLoading
//            }
//            .addTo(disposeBag)
//
//        viewModel.isError
//            .filter { it }
//            .subscribe {
//                showMessage(R.string.error_data_loading)
//            }
//            .addTo(disposeBag)
//    }

    companion object {
        fun newInstance(
            gameDetails: GameDetails?
        ): BoxScoreFragment {
            val fragment = BoxScoreFragment()
            val bundle = bundleOf("gameDetails" to gameDetails)
            fragment.arguments = bundle
            return fragment
        }
    }
}
