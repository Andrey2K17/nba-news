package ru.pg13lac.nbanews.presentation.viewModel.summary_game_details

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.BiFunction
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import ru.pg13lac.nbanews.domain.entity.*
import ru.pg13lac.nbanews.domain.entity.gameLeaders.GameBoxScoreLeaders
import ru.pg13lac.nbanews.domain.entity.gameLeaders.toLeaders
import ru.pg13lac.nbanews.domain.entity.summaryGameDetails.SummaryGameDetails
import ru.pg13lac.nbanews.domain.entity.summaryGameDetails.toGameDetails
import ru.pg13lac.nbanews.domain.entity.summaryGameDetails.toTeamPointsForQuarter
import ru.pg13lac.nbanews.domain.interactor.GamesInteractor
import java.util.concurrent.TimeUnit
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

    fun getSummaryGameDetails(gameId: String) {
        reset()
        interactor.getSummaryGameDetails(gameId).zipWith(
            //На сервере ограничение один запрос в секунду
            interactor.getGameBoxScore(gameId).delaySubscription(1, TimeUnit.SECONDS),
            BiFunction<SummaryGameDetails, GameBoxScoreLeaders, FullGameDetails> { p0, p1 ->
                FullGameDetails(p0, p1)
            }
        )
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.onNext(true) }
            .doFinally { isLoading.onNext(false) }
            .subscribeBy(
                onSuccess = {
                    gameDetails.onNext(it.summaryGameDetails.toGameDetails())
                    teamPointsForQuarter.onNext(it.summaryGameDetails.toTeamPointsForQuarter())
                    gameLeaders.onNext(it.gameBoxScoreLeaders.toLeaders())
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