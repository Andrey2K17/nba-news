package ru.pg13lac.nbanews.presentation.viewModel.player_profile

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import ru.pg13lac.nbanews.domain.entity.playerProfile.PlayerProfile
import ru.pg13lac.nbanews.domain.entity.teamProfile.TeamProfile
import ru.pg13lac.nbanews.domain.interactor.GamesInteractor
import javax.inject.Inject

class PlayerProfileViewModel @Inject constructor(
    private val interactor: GamesInteractor
) : ViewModel() {
    val playerProfile: BehaviorSubject<PlayerProfile> = BehaviorSubject.create()
    val isLoading: PublishSubject<Boolean> = PublishSubject.create()
    val isError: PublishSubject<Boolean> = PublishSubject.create()

    private val disposable = CompositeDisposable()

    fun getPlayerProfile(playerId: String) {
        reset()
        interactor.getPlayerProfile(playerId)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.onNext(true) }
            .doFinally { isLoading.onNext(false) }
            .subscribeBy(
                onSuccess = {
                    playerProfile.onNext(it)
                    isError.onNext(false)
                },
                onError = {
                    isError.onNext(true)
                }
            )
    }

    private fun reset() {
//        listOfPlayers.onNext(emptyList())
        disposable.clear()
    }

    override fun onCleared() {
        disposable.clear()
    }
}