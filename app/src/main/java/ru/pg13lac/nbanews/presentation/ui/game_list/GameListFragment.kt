package ru.pg13lac.nbanews.presentation.ui.game_list

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.common.isVisible
import ru.pg13lac.nbanews.databinding.FragmentGameListBinding
import ru.pg13lac.nbanews.domain.entity.OnClickCallback
import ru.pg13lac.nbanews.presentation.ui.base.BaseFragment
import ru.pg13lac.nbanews.presentation.viewModel.game_list.GameListViewModel
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

class GameListFragment : BaseFragment<FragmentGameListBinding>(R.layout.fragment_game_list) {

    @Inject
    lateinit var viewModel: GameListViewModel

    private val disposeBag = CompositeDisposable()

    @Inject
    lateinit var gameListAdapter: GameListAdapter
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentGameListBinding {
        return FragmentGameListBinding.inflate(inflater, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (gameListAdapter.mDataList.isEmpty()) {
            viewModel.getDailyGames("2020", "02", "10")
        }

        gameListAdapter.attachCallback(object : OnClickCallback {
            override fun routeTo(gameId: String, vTeam: String, hTeam: String) {
                routeToDetails(gameId, vTeam, hTeam)
            }
        })
        binding.rvGameList.layoutManager = LinearLayoutManager(activity)
        binding.rvGameList.adapter = gameListAdapter
    }


    fun routeToDetails(gameId: String, vTeam: String, hTeam: String) {
        val bundle = bundleOf("gameId" to gameId, "vTeam" to vTeam, "hTeam" to hTeam)
        findNavController().navigate(R.id.actionToGameDetails, bundle)
    }

    @SuppressLint("SimpleDateFormat")
    private fun showSelectedDate(): String {
        val cal = Calendar.getInstance()
        cal.add(Calendar.DATE, -1)
        val dateBefore1Days = cal.time
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
//        return dateFormat.format(dateBefore1Days)
        return "2020-03-02"
    }

    override fun setListeners() {
        binding.srlGameList.setOnRefreshListener {
            viewModel.getDailyGames("2020", "02", "10")
        }
    }

    override fun setModelBindings() {
        viewModel.listOfGames
            .subscribe {
                gameListAdapter.mDataList = it
            }
            .addTo(disposeBag)

        viewModel.isLoading
            .subscribe { isLoading ->
                binding.srlGameList?.isRefreshing = isLoading
            }
            .addTo(disposeBag)

        viewModel.isError
            .filter { it }
            .subscribe {
                showMessage(R.string.error_data_loading)
            }
            .addTo(disposeBag)
        viewModel.emptyList
            .subscribe {
                binding.tvEmptyList.isVisible = it
            }
            .addTo(disposeBag)
    }
}
