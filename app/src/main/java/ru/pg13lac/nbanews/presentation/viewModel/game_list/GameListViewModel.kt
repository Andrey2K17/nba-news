package ru.pg13lac.nbanews.presentation.viewModel.game_list

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import ru.pg13lac.nbanews.domain.entity.GameItem
import ru.pg13lac.nbanews.domain.entity.Games
import ru.pg13lac.nbanews.domain.interactor.GameListInteractor
import javax.inject.Inject

class GameListViewModel @Inject constructor(
    private val interactor: GameListInteractor
) : ViewModel() {
    val listOfGames: BehaviorSubject<List<GameItem>> = BehaviorSubject.create()
    val isLoading: PublishSubject<Boolean> = PublishSubject.create()
    val isError: PublishSubject<Boolean> = PublishSubject.create()

    private var list = mutableListOf<GameItem>()

    private val disposable = CompositeDisposable()

    private fun mapGames(games: Games) {
        for (i in 0 until games.resultSets[1].rowSet!!.size - 1 step 2) {
            list.add(
                GameItem(
                    games.resultSets[1].rowSet!![i][4],
                    games.resultSets[1].rowSet!![i + 1][4],
                    games.resultSets[1].rowSet!![i][6],
                    games.resultSets[1].rowSet!![i + 1][6],
                    games.resultSets[1].rowSet!![i][22],
                    games.resultSets[1].rowSet!![i + 1][22],
                    games.resultSets[0].rowSet!![i / 2][4],
                    games.resultSets[1].rowSet!![i][2]
                )
            )
        }
    }

    fun getGames(day: String) {
        interactor.getGames(day)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.onNext(true) }
            .doFinally { isLoading.onNext(false) }
            .subscribeBy(
                onSuccess = {
                    mapGames(it)
                    listOfGames.onNext(list)
                    isError.onNext(false)
                },
                onError = {
                    isError.onNext(true)
                }
            ).addTo(disposable)
    }
}