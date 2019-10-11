package ru.pg13lac.nbanews.presentation.viewModel.summary

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import ru.pg13lac.nbanews.domain.entity.BoxScore
import ru.pg13lac.nbanews.domain.entity.GameDetails
import ru.pg13lac.nbanews.domain.entity.Games
import ru.pg13lac.nbanews.domain.entity.TeamComparison
import ru.pg13lac.nbanews.domain.interactor.GamesInteractor
import javax.inject.Inject

class GameDetailsViewModel @Inject constructor(
    private val interactor: GamesInteractor
) : ViewModel() {
    val gameDetails: BehaviorSubject<GameDetails> = BehaviorSubject.create()
    val isLoading: PublishSubject<Boolean> = PublishSubject.create()
    val isError: PublishSubject<Boolean> = PublishSubject.create()

    private val boxScoreList = mutableListOf<BoxScore>()

    private val disposable = CompositeDisposable()

    private var teamComparison: Pair<TeamComparison?, TeamComparison?>? = null

    private fun mapDataToSummary(game: Games) {
        reset()
        for (i in game.resultSets[0].rowSet!!.indices) {
            boxScoreList.add(
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

        if (!game.resultSets[1].rowSet.isNullOrEmpty()) {
            teamComparison = Pair(
                TeamComparison(
                    TEAM_ABBREVIATION = game.resultSets[1].rowSet!![0][3],
                    FGM = game.resultSets[1].rowSet!![0][6],
                    FGA = game.resultSets[1].rowSet!![0][7],
                    FG_PCT = game.resultSets[1].rowSet!![0][8],
                    FG3M = game.resultSets[1].rowSet!![0][9],
                    FG3A = game.resultSets[1].rowSet!![0][10],
                    FG3_PCT = game.resultSets[1].rowSet!![0][11],
                    FTM = game.resultSets[1].rowSet!![0][12],
                    FTA = game.resultSets[1].rowSet!![0][13],
                    FT_PCT = game.resultSets[1].rowSet!![0][14],
                    AST = game.resultSets[1].rowSet!![0][18],
                    REB = game.resultSets[1].rowSet!![0][17],
                    O_REB = game.resultSets[1].rowSet!![0][15],
                    D_REB = game.resultSets[1].rowSet!![0][16],
                    STL = game.resultSets[1].rowSet!![0][19],
                    BLK = game.resultSets[1].rowSet!![0][20],
                    TO = game.resultSets[1].rowSet!![0][21]
                ), TeamComparison(
                    TEAM_ABBREVIATION = game.resultSets[1].rowSet!![1][3],
                    FGM = game.resultSets[1].rowSet!![1][6],
                    FGA = game.resultSets[1].rowSet!![1][7],
                    FG_PCT = game.resultSets[1].rowSet!![1][8],
                    FG3M = game.resultSets[1].rowSet!![1][9],
                    FG3A = game.resultSets[1].rowSet!![1][10],
                    FG3_PCT = game.resultSets[1].rowSet!![1][11],
                    FTM = game.resultSets[1].rowSet!![1][12],
                    FTA = game.resultSets[1].rowSet!![1][13],
                    FT_PCT = game.resultSets[1].rowSet!![1][14],
                    AST = game.resultSets[1].rowSet!![1][18],
                    REB = game.resultSets[1].rowSet!![1][17],
                    O_REB = game.resultSets[1].rowSet!![1][15],
                    D_REB = game.resultSets[1].rowSet!![1][16],
                    STL = game.resultSets[1].rowSet!![1][19],
                    BLK = game.resultSets[1].rowSet!![1][20],
                    TO = game.resultSets[1].rowSet!![1][21]
                )
            )
        }
    }

    fun getSummary(gameId: String) {
        interactor.getGameDetails(gameId)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.onNext(true) }
            .doFinally { isLoading.onNext(false) }
            .subscribeBy(
                onSuccess = {
                    mapDataToSummary(it)
                    gameDetails.onNext(GameDetails(boxScoreList, teamComparison))
                    isError.onNext(false)
                },
                onError = {
                    isError.onNext(true)
                }
            ).addTo(disposable)
    }

    private fun reset() {
        boxScoreList.clear()
        gameDetails.onNext(GameDetails())
        teamComparison = Pair(null, null)
        disposable.clear()
    }
}