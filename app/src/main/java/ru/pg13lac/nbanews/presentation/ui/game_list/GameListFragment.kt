package ru.pg13lac.nbanews.presentation.ui.game_list

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import kotlinx.android.synthetic.main.fragment_game_list.*
import ru.pg13lac.nbanews.App
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.domain.entity.OnClickCallback
import ru.pg13lac.nbanews.presentation.ui.base.BaseFragment
import ru.pg13lac.nbanews.presentation.viewModel.game_list.GameListViewModel
import javax.inject.Inject

class GameListFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_game_list

    @Inject
    lateinit var viewModel: GameListViewModel

    private val disposeBag = CompositeDisposable()

    private lateinit var gameListAdapter: GameListAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.instance.component.inject(this)
        setBindings()

        gameListAdapter = GameListAdapter()
        gameListAdapter.attachCallback(object : OnClickCallback {
            override fun routeTo(data: String) {
                routeToDetails(data)
            }

        })
        rvGameList.layoutManager = LinearLayoutManager(activity)
        rvGameList.adapter = gameListAdapter
        viewModel.getGames("01.21.18")
    }

    fun routeToDetails(data: String) {
        val bundle = Bundle()
        bundle.putString("gameId", data)
        findNavController().navigate(R.id.actionToGameDetails, bundle)
    }

    private fun setBindings() {
        viewModel.listOfGames
            .subscribe {
                gameListAdapter.mDataList = it
            }
            .addTo(disposeBag)
    }
}
