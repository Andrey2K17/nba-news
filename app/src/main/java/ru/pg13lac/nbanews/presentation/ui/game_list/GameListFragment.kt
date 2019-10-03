package ru.pg13lac.nbanews.presentation.ui.game_list

import android.os.Bundle
import android.view.View
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_game_list.*
import ru.pg13lac.nbanews.App
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.domain.entity.GameItem
import ru.pg13lac.nbanews.domain.entity.GameLeaders
import ru.pg13lac.nbanews.domain.entity.OnClickCallback
import ru.pg13lac.nbanews.domain.entity.TeamPointsForQuarter
import ru.pg13lac.nbanews.presentation.ui.base.BaseFragment
import ru.pg13lac.nbanews.presentation.viewModel.game_list.GameListViewModel
import javax.inject.Inject

class GameListFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_game_list

    @Inject
    lateinit var viewModel: GameListViewModel

    private val disposeBag = CompositeDisposable()

    @Inject
    lateinit var gameListAdapter: GameListAdapter

    private var teamPointsForQuarterList: List<TeamPointsForQuarter>? = null
    private var gameLeadersList: List<GameLeaders>? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.instance.component.inject(this)

        gameListAdapter.attachCallback(object : OnClickCallback {
            override fun routeTo(gameId: String) {
                routeToDetails(gameId)
            }
        })
        rvGameList.layoutManager = LinearLayoutManager(activity)
        rvGameList.adapter = gameListAdapter
        viewModel.getGames("01.21.18")
    }

    fun routeToDetails(gameId: String) {
        val bundle = bundleOf(
            "gameInfo" to teamPointsForQuarterList?.first { it.game_id == gameId },
            "gameLeaders" to gameLeadersList?.filter { it.game_id == gameId }
        )
        findNavController().navigate(R.id.actionToGameDetails, bundle)
    }

    override fun setListeners() {
        srlGameList.setOnRefreshListener {
            viewModel.getGames("01.22.18")
        }
    }

    override fun setModelBindings() {
        viewModel.listOfGames
            .subscribe {
                gameListAdapter.mDataList = it.gameScoreList as List<GameItem>
                teamPointsForQuarterList = it.teamPointsForQuarterList
                gameLeadersList = it.gameLeaders
            }
            .addTo(disposeBag)

        viewModel.isLoading
            .subscribe { isLoading ->
                srlGameList.isRefreshing = isLoading
            }
            .addTo(disposeBag)

        viewModel.isError
            .filter { it }
            .subscribe {
                showMessage(R.string.error_data_loading)
            }
            .addTo(disposeBag)
    }
}
