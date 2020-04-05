package ru.pg13lac.nbanews.presentation.viewModel.box_score

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import ru.pg13lac.nbanews.App
import ru.pg13lac.nbanews.domain.entity.BoxScore
import ru.pg13lac.nbanews.domain.entity.testGameStatistic.toBoxScore
import ru.pg13lac.nbanews.domain.interactor.GamesInteractor
import javax.inject.Inject

class BoxScoreViewModel @Inject constructor(
    private val interactor: GamesInteractor
) : ViewModel() {
    val boxScore: BehaviorSubject<List<BoxScore>> = BehaviorSubject.create()
    val isLoading: PublishSubject<Boolean> = PublishSubject.create()
    val isError: PublishSubject<Boolean> = PublishSubject.create()

    private val disposable = CompositeDisposable()

    fun getBoxScore(gameId: String) {
        Log.d(App.TAG, gameId)
        reset()
        interactor.getTestPlayerStatistic(gameId)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.onNext(true) }
            .doFinally { isLoading.onNext(false) }
            .subscribeBy(
                onSuccess = {
                    boxScore.onNext(it.toBoxScore())
                    isError.onNext(false)
                },
                onError = {
                    isError.onNext(true)
                }
            ).addTo(disposable)
    }

    private fun reset() {
        boxScore.onNext(emptyList())
        disposable.clear()
    }

    override fun onCleared() {
        disposable.clear()
    }
}