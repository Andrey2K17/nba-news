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
import ru.pg13lac.nbanews.domain.entity.MatchResults
import ru.pg13lac.nbanews.domain.entity.TeamPointsForQuarter
import ru.pg13lac.nbanews.domain.interactor.GamesInteractor
import javax.inject.Inject

class GameListViewModel @Inject constructor(
    private val interactor: GamesInteractor
) : ViewModel() {
    val listOfGames: BehaviorSubject<MatchResults> = BehaviorSubject.create()
    val isLoading: PublishSubject<Boolean> = PublishSubject.create()
    val isError: PublishSubject<Boolean> = PublishSubject.create()

    private var list = mutableListOf<GameItem>()
    private var quarterList = mutableListOf<TeamPointsForQuarter>()

    private val disposable = CompositeDisposable()

    private fun mapGames(games: Games) {
        val result = games.resultSets[1].rowSet!!
        reset()
        for (i in 0 until games.resultSets[1].rowSet!!.size - 1 step 2) {
            list.add(
                GameItem(
                    result[i][4],
                    result[i + 1][4],
                    result[i][6],
                    result[i + 1][6],
                    result[i][22],
                    result[i + 1][22],
                    result[i / 2][4],
                    result[i][2]
                )
            )
            quarterList.add(
                TeamPointsForQuarter(
                    result[i][8],
                    result[i][9],
                    result[i][10],
                    result[i][11],
                    result[i][22],
                    result[i + 1][8],
                    result[i + 1][9],
                    result[i + 1][10],
                    result[i + 1][11],
                    result[i + 1][22],
                    result[i][2],
                    result[i][6],
                    result[i + 1][6]
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
                    listOfGames.onNext(MatchResults(list, quarterList))
                    isError.onNext(false)
                },
                onError = {
                    isError.onNext(true)
                }
            ).addTo(disposable)
    }

    private fun reset() {
        listOfGames.onNext(MatchResults(emptyList(), emptyList()))
        disposable.clear()
        list.clear()
    }
}