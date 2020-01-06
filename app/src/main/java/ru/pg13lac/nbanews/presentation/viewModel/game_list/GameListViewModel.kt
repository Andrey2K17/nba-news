package ru.pg13lac.nbanews.presentation.viewModel.game_list

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import ru.pg13lac.nbanews.common.nameTeams
import ru.pg13lac.nbanews.domain.entity.GameItem
import ru.pg13lac.nbanews.domain.entity.scoreboard.Scoreboard
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

    fun getGames(date: String) {
        reset()
        interactor.getGames(date)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.onNext(true) }
            .doFinally { isLoading.onNext(false) }
            .subscribeBy(
                onSuccess = {
                    listOfGames.onNext(mapGames(it))
                    if (it.games.isEmpty()){
                        emptyList.onNext(true)
                    }
                    isError.onNext(false)
                },
                onError = {
                    isError.onNext(true)
                }
            ).addTo(disposable)
    }

    private fun mapGames(scoreboard: Scoreboard): MutableList<GameItem> {
        val scoreboardList = mutableListOf<GameItem>()
        for (i in 0 until scoreboard.games.size - 1) {
            scoreboardList.add(
                GameItem(
                    left_img = scoreboard.games[i].hTeam.triCode,
                    right_img = scoreboard.games[i].vTeam.triCode,
                    left_team_name = nameTeams[scoreboard.games[i].hTeam.teamId],
                    right_team_name = nameTeams[scoreboard.games[i].vTeam.teamId],
                    left_team_pts = scoreboard.games[i].hTeam.score,
                    right_team_pts = scoreboard.games[i].vTeam.score,
                    game_status = scoreboard.games[i].statusNum.toString(),
                    gameId = scoreboard.games[i].gameId
                )
            )
        }
        return scoreboardList
    }

    private fun reset() {
        listOfGames.onNext(emptyList())
        disposable.clear()
    }

    override fun onCleared() {
        disposable.clear()
    }
}