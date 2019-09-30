package ru.pg13lac.nbanews.presentation.viewModel.game_list

import android.util.Log
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import ru.pg13lac.nbanews.domain.entity.Games
import ru.pg13lac.nbanews.domain.interactor.GameListInteractor
import javax.inject.Inject

class GameListViewModel @Inject constructor(
    private val interactor: GameListInteractor
) : ViewModel() {
    val listOfGames: BehaviorSubject<Games> = BehaviorSubject.create()
    val isLoading: PublishSubject<Boolean> = PublishSubject.create()
    val isError: PublishSubject<Boolean> = PublishSubject.create()

    private val disposable = CompositeDisposable()

    fun getGames(day: String) {
        interactor.getGames(day)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.onNext(true) }
            .doFinally { isLoading.onNext(false) }
            .subscribeBy(
                onSuccess = {
                    listOfGames.onNext(it)
                    isError.onNext(false)
                },
                onError = {
                    isError.onNext(true)
                }
            ).addTo(disposable)
    }
}