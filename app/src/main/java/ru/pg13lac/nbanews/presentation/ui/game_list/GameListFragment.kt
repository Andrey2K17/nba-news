package ru.pg13lac.nbanews.presentation.ui.game_list

import android.os.Bundle
import android.util.Log
import android.view.View
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import ru.pg13lac.nbanews.App
import ru.pg13lac.nbanews.R
import ru.pg13lac.nbanews.presentation.ui.base.BaseFragment
import ru.pg13lac.nbanews.presentation.viewModel.game_list.GameListViewModel
import javax.inject.Inject

class GameListFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_game_list

    @Inject
    lateinit var viewModel: GameListViewModel

    private val disposeBag = CompositeDisposable()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        App.instance.component.inject(this)
        setBindings()
        viewModel.getGames("01.21.18")
    }

    private fun setBindings() {
        viewModel.listOfGames
            .subscribe {
                Log.d("test123", it.toString())
            }
            .addTo(disposeBag)
    }
}
