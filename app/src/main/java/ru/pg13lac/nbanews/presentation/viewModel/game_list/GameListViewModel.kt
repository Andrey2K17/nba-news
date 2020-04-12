package ru.pg13lac.nbanews.presentation.viewModel.game_list

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import ru.pg13lac.nbanews.domain.entity.GameItem
import ru.pg13lac.nbanews.domain.entity.dailyGames.toGameDetails
import ru.pg13lac.nbanews.domain.interactor.GamesInteractor
import javax.inject.Inject

class GameListViewModel @Inject constructor(
    private val interactor: GamesInteractor
) : ViewModel() {
    val listOfGames: BehaviorSubject<List<GameItem>> = BehaviorSubject.create()
    val isLoading: PublishSubject<Boolean> = PublishSubject.create()
    val isError: PublishSubject<Boolean> = PublishSubject.create()
    val emptyList: PublishSubject<Boolean> = PublishSubject.create()

    private val disposable = CompositeDisposable()

    fun getDailyGames(year: String, month: String, day: String) {
        reset()
        interactor.getDailyGames(year, month, day)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.onNext(true) }
            .doFinally { isLoading.onNext(false) }
            .subscribeBy(
                onSuccess = {
                    listOfGames.onNext(it.toGameDetails())
                    if (it.games.isEmpty()) {
                        emptyList.onNext(true)
                    }
                    isError.onNext(false)
                },
                onError = {
                    isError.onNext(true)
                }
            )
    }

    private fun reset() {
        listOfGames.onNext(emptyList())
        disposable.clear()
    }

    override fun onCleared() {
        disposable.clear()
    }
}