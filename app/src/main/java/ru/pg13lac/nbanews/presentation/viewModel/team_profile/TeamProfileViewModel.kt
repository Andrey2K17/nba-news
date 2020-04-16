package ru.pg13lac.nbanews.presentation.viewModel.team_profile

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import ru.pg13lac.nbanews.domain.entity.teamProfile.TeamProfile
import ru.pg13lac.nbanews.domain.interactor.GamesInteractor
import javax.inject.Inject

class TeamProfileViewModel @Inject constructor(
    private val interactor: GamesInteractor
) : ViewModel() {
    val teamProfile: BehaviorSubject<TeamProfile> = BehaviorSubject.create()
    val isLoading: PublishSubject<Boolean> = PublishSubject.create()
    val isError: PublishSubject<Boolean> = PublishSubject.create()
    val emptyList: PublishSubject<Boolean> = PublishSubject.create()

    private val disposable = CompositeDisposable()

    fun getTeamProfile(teamId: String) {
        reset()
        interactor.getTeamProfile(teamId)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.onNext(true) }
            .doFinally { isLoading.onNext(false) }
            .subscribeBy(
                onSuccess = {
                    teamProfile.onNext(it)
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