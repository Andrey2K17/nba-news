package ru.pg13lac.nbanews.presentation.viewModel.game_list

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import ru.pg13lac.nbanews.domain.entity.GameItem
import ru.pg13lac.nbanews.domain.entity.testGames.TestGames
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
                    if (it.api.games.isEmpty()) {
                        emptyList.onNext(true)
                    }
                    isError.onNext(false)
                },
                onError = {
                    isError.onNext(true)
                }
            ).addTo(disposable)
    }

    private fun mapGames(testGames: TestGames): MutableList<GameItem> {
        val scoreboardList = mutableListOf<GameItem>()
        for (i in testGames.api.games.indices) {
            scoreboardList.add(
                GameItem(
                    left_img = testGames.api.games[i].vTeam.shortName,
                    right_img = testGames.api.games[i].hTeam.shortName,
                    left_team_name = testGames.api.games[i].vTeam.nickName,
                    right_team_name = testGames.api.games[i].hTeam.nickName,
                    left_team_pts = testGames.api.games[i].vTeam.score.points,
                    right_team_pts = testGames.api.games[i].hTeam.score.points,
                    game_status = testGames.api.games[i].statusGame,
                    gameId = testGames.api.games[i].gameId
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