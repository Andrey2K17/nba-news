package ru.pg13lac.nbanews.presentation.viewModel.summary_game_details

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import ru.pg13lac.nbanews.domain.entity.*
import ru.pg13lac.nbanews.domain.entity.testCompleteStats.TestCompleteStats
import ru.pg13lac.nbanews.domain.entity.testPlayerStats.toGameDetails
import ru.pg13lac.nbanews.domain.entity.testPlayerStats.toGameLeaders
import ru.pg13lac.nbanews.domain.entity.testPlayerStats.toTeamPointsForQuarter
import ru.pg13lac.nbanews.domain.entity.testGameDetails.TestGameDetails
import ru.pg13lac.nbanews.domain.entity.testGameStatistic.TestGameStatistic
import ru.pg13lac.nbanews.domain.interactor.GamesInteractor
import javax.inject.Inject

class SummaryGameDetailsViewModel @Inject constructor(
    private val interactor: GamesInteractor
) : ViewModel() {
    val gameDetails: BehaviorSubject<GameDetails> = BehaviorSubject.create()
    val teamPointsForQuarter: BehaviorSubject<TeamPointsForQuarter> = BehaviorSubject.create()
    val gameLeaders: BehaviorSubject<Pair<GameLeaders, GameLeaders>> = BehaviorSubject.create()
    val isLoading: PublishSubject<Boolean> = PublishSubject.create()
    val isError: PublishSubject<Boolean> = PublishSubject.create()

    private val disposable = CompositeDisposable()

    fun getGamesDetails(gameId: String) {
        reset()
        interactor.getTestGameDetails(gameId).zipWith(
            interactor.getTestGameStatistic(gameId),
            BiFunction<TestGameDetails, TestGameStatistic, TestCompleteStats> {
                    p0, p1 -> TestCompleteStats(p0, p1)
            }).observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.onNext(true) }
            .doFinally { isLoading.onNext(false) }
            .subscribeBy(
                onSuccess = {
                    gameDetails.onNext(it.toGameDetails())
                    teamPointsForQuarter.onNext(it.toTeamPointsForQuarter())
                    gameLeaders.onNext(it.toGameLeaders())
                    isError.onNext(false)
                },
                onError = {
                    isError.onNext(true)
                }
            )
    }

    private fun reset() {
        gameDetails.onNext(GameDetails())
        teamPointsForQuarter.onNext(TeamPointsForQuarter())
        gameLeaders.onNext(Pair(GameLeaders(), GameLeaders()))
        disposable.clear()
    }

    override fun onCleared() {
        disposable.clear()
    }
}