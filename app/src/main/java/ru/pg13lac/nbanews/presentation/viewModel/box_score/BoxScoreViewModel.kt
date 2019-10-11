package ru.pg13lac.nbanews.presentation.viewModel.box_score

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import ru.pg13lac.nbanews.domain.entity.BoxScore
import ru.pg13lac.nbanews.domain.entity.Games
import ru.pg13lac.nbanews.domain.interactor.GamesInteractor
import javax.inject.Inject

class BoxScoreViewModel @Inject constructor(
    private val interactor: GamesInteractor
) : ViewModel() {
    val boxScore: BehaviorSubject<List<BoxScore>> = BehaviorSubject.create()
    val isLoading: PublishSubject<Boolean> = PublishSubject.create()
    val isError: PublishSubject<Boolean> = PublishSubject.create()

    private val list = mutableListOf<BoxScore>()

    private val disposable = CompositeDisposable()

    private fun mapToBoxScore(game: Games) {
        reset()
        for (i in game.resultSets[0].rowSet!!.indices) {
            list.add(
                BoxScore(
                    game.resultSets[0].rowSet!![i][2],
                    game.resultSets[0].rowSet!![i][5].substringBefore(" "),
                    game.resultSets[0].rowSet!![i][5].substringAfter(" "),
                    game.resultSets[0].rowSet!![i][26],
                    game.resultSets[0].rowSet!![i][4],
                    game.resultSets[0].rowSet!![i][20],
                    game.resultSets[0].rowSet!![i][21]
                )
            )
        }
    }

    fun getBoxScore(gameId: String) {
        interactor.getGameDetails(gameId)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.onNext(true) }
            .doFinally { isLoading.onNext(false) }
            .subscribeBy(
                onSuccess = {
                    mapToBoxScore(it)
                    boxScore.onNext(list)
                    isError.onNext(false)
                },
                onError = {
                    isError.onNext(true)
                }
            ).addTo(disposable)
    }

    private fun reset() {
        list.clear()
        boxScore.onNext(emptyList())
        disposable.clear()
    }
}