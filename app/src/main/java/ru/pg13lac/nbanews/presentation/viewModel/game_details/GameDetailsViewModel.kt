package ru.pg13lac.nbanews.presentation.viewModel.game_details

import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.addTo
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.BehaviorSubject
import io.reactivex.subjects.PublishSubject
import ru.pg13lac.nbanews.common.nameTeams
import ru.pg13lac.nbanews.domain.entity.*
import ru.pg13lac.nbanews.domain.interactor.GamesInteractor
import javax.inject.Inject

class GameDetailsViewModel @Inject constructor(
    private val interactor: GamesInteractor
) : ViewModel() {
    val gameDetails: BehaviorSubject<GameDetails> = BehaviorSubject.create()
    val teamPointsForQuarter: BehaviorSubject<TeamPointsForQuarter> = BehaviorSubject.create()
    val gameLeaders: BehaviorSubject<Pair<GameLeaders, GameLeaders>> = BehaviorSubject.create()
    val isLoading: PublishSubject<Boolean> = PublishSubject.create()
    val isError: PublishSubject<Boolean> = PublishSubject.create()

    private val disposable = CompositeDisposable()

    fun getGameDetails(date: String, gameId: String) {
        reset()
        interactor.getGameDetails(date, gameId)
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.onNext(true) }
            .doFinally { isLoading.onNext(false) }
            .subscribeBy(
                onSuccess = {
                    gameDetails.onNext(mapGameDetails(it))
                    teamPointsForQuarter.onNext(mapTeamPointsForQuarter(it))
                    gameLeaders.onNext(mapGameLeaders(it))
                    isError.onNext(false)
                },
                onError = {
                    isError.onNext(true)
                }
            ).addTo(disposable)
    }

    private fun mapGameDetails(
        gameDetails: ru.pg13lac.nbanews.domain.entity.gameDetails.GameDetails
    ): GameDetails {
        val boxScoreList = mutableListOf<BoxScore>()
        val teamComparison: Pair<TeamComparison?, TeamComparison?>?
        val gameDetailsInfo: GameDetailsInfo?
        val (activePlayers) = gameDetails.stats

        for (i in gameDetails.stats.activePlayers.indices) {
            boxScoreList.add(
                BoxScore(
                    team_name = nameTeams[activePlayers[i].teamId],
                    player_name = activePlayers[i].firstName,
                    player_surname = activePlayers[i].lastName,
                    player_pts = activePlayers[i].points,
                    player_id = activePlayers[i].personId,
                    player_reb = activePlayers[i].totReb,
                    player_ast = activePlayers[i].assists
                )
            )
        }
        teamComparison = Pair(
            first = TeamComparison(
                TEAM_ABBREVIATION = gameDetails.basicGameData.vTeam.triCode,
                FGM = gameDetails.stats.vTeam.totals.fgm,
                FGA = gameDetails.stats.vTeam.totals.fga,
                FG_PCT = gameDetails.stats.vTeam.totals.fgp,
                FG3M = gameDetails.stats.vTeam.totals.tpm,
                FG3A = gameDetails.stats.vTeam.totals.tpa,
                FG3_PCT = gameDetails.stats.vTeam.totals.tpp,
                FTM = gameDetails.stats.vTeam.totals.ftm,
                FTA = gameDetails.stats.vTeam.totals.fta,
                FT_PCT = gameDetails.stats.vTeam.totals.ftp,
                AST = gameDetails.stats.vTeam.totals.assists,
                REB = gameDetails.stats.vTeam.totals.totReb,
                O_REB = gameDetails.stats.vTeam.totals.offReb,
                D_REB = gameDetails.stats.vTeam.totals.defReb,
                STL = gameDetails.stats.vTeam.totals.steals,
                BLK = gameDetails.stats.vTeam.totals.blocks,
                TO = gameDetails.stats.vTeam.totals.turnovers
            ),
            second = TeamComparison(
                TEAM_ABBREVIATION = gameDetails.basicGameData.hTeam.triCode,
                FGM = gameDetails.stats.hTeam.totals.fgm,
                FGA = gameDetails.stats.hTeam.totals.fga,
                FG_PCT = gameDetails.stats.hTeam.totals.fgp,
                FG3M = gameDetails.stats.hTeam.totals.tpm,
                FG3A = gameDetails.stats.hTeam.totals.tpa,
                FG3_PCT = gameDetails.stats.hTeam.totals.tpp,
                FTM = gameDetails.stats.hTeam.totals.ftm,
                FTA = gameDetails.stats.hTeam.totals.fta,
                FT_PCT = gameDetails.stats.hTeam.totals.ftp,
                AST = gameDetails.stats.hTeam.totals.assists,
                REB = gameDetails.stats.hTeam.totals.totReb,
                O_REB = gameDetails.stats.hTeam.totals.offReb,
                D_REB = gameDetails.stats.hTeam.totals.defReb,
                STL = gameDetails.stats.hTeam.totals.steals,
                BLK = gameDetails.stats.hTeam.totals.blocks,
                TO = gameDetails.stats.hTeam.totals.turnovers
            ))
        gameDetailsInfo = GameDetailsInfo(
            gameId = gameDetails.basicGameData.gameId,
            leftTeam = nameTeams.getValue(gameDetails.basicGameData.vTeam.teamId),
            rightTeam = nameTeams.getValue(gameDetails.basicGameData.hTeam.teamId)
        )
        return GameDetails(boxScoreList, teamComparison, gameDetailsInfo)
    }

    private fun mapTeamPointsForQuarter(
        gameDetails: ru.pg13lac.nbanews.domain.entity.gameDetails.GameDetails
    ): TeamPointsForQuarter {
        return TeamPointsForQuarter(
            left_team_q1 = gameDetails.basicGameData.vTeam.linescore[0].score,
            left_team_q2 = gameDetails.basicGameData.vTeam.linescore[1].score,
            left_team_q3 = gameDetails.basicGameData.vTeam.linescore[2].score,
            left_team_q4 = gameDetails.basicGameData.vTeam.linescore[3].score,
            left_team_tot = gameDetails.basicGameData.vTeam.score,
            right_team_q1 = gameDetails.basicGameData.hTeam.linescore[0].score,
            right_team_q2 = gameDetails.basicGameData.hTeam.linescore[1].score,
            right_team_q3 = gameDetails.basicGameData.hTeam.linescore[2].score,
            right_team_q4 = gameDetails.basicGameData.hTeam.linescore[3].score,
            right_team_tot = gameDetails.basicGameData.hTeam.score,
            game_id = gameDetails.basicGameData.gameId,
            leftTeam = nameTeams.getValue(gameDetails.basicGameData.vTeam.teamId),
            rightTeam = nameTeams.getValue(gameDetails.basicGameData.hTeam.teamId)
        )
    }

    private fun mapGameLeaders(
        gameDetails: ru.pg13lac.nbanews.domain.entity.gameDetails.GameDetails
    ) : Pair<GameLeaders, GameLeaders> {
        return Pair(
            GameLeaders(
                game_id = gameDetails.basicGameData.gameId,
                player_pts = Points(
                    id = gameDetails.stats.vTeam.leaders.points.players[0].personId,
                    name = "${gameDetails.stats.vTeam.leaders.points.players[0].firstName} " +
                            gameDetails.stats.vTeam.leaders.points.players[0].lastName,
                    amount = gameDetails.stats.vTeam.leaders.points.value
                ),
                player_reb = Rebounds(
                    id = gameDetails.stats.vTeam.leaders.rebounds.players[0].personId,
                    name = "${gameDetails.stats.vTeam.leaders.rebounds.players[0].firstName} " +
                            gameDetails.stats.vTeam.leaders.rebounds.players[0].lastName,
                    amount = gameDetails.stats.vTeam.leaders.rebounds.value
                ),
                player_ast = Assists(
                    id = gameDetails.stats.vTeam.leaders.assists.players[0].personId,
                    name = "${gameDetails.stats.vTeam.leaders.assists.players[0].firstName} " +
                            gameDetails.stats.vTeam.leaders.assists.players[0].lastName,
                    amount = gameDetails.stats.vTeam.leaders.assists.value
                ),
                team_name = nameTeams.getValue(gameDetails.basicGameData.vTeam.teamId)
            ),
        GameLeaders(
            game_id = gameDetails.basicGameData.gameId,
            player_pts = Points(
                id = gameDetails.stats.hTeam.leaders.points.players[0].personId,
                name = "${gameDetails.stats.hTeam.leaders.points.players[0].firstName} " +
                        gameDetails.stats.hTeam.leaders.points.players[0].lastName,
                amount = gameDetails.stats.hTeam.leaders.points.value
            ),
            player_reb = Rebounds(
                id = gameDetails.stats.hTeam.leaders.rebounds.players[0].personId,
                name = "${gameDetails.stats.hTeam.leaders.rebounds.players[0].firstName} " +
                        gameDetails.stats.hTeam.leaders.rebounds.players[0].lastName,
                amount = gameDetails.stats.hTeam.leaders.rebounds.value
            ),
            player_ast = Assists(
                id = gameDetails.stats.hTeam.leaders.assists.players[0].personId,
                name = "${gameDetails.stats.hTeam.leaders.assists.players[0].firstName} " +
                        gameDetails.stats.hTeam.leaders.assists.players[0].lastName,
                amount = gameDetails.stats.hTeam.leaders.assists.value
            ),
            team_name = nameTeams.getValue(gameDetails.basicGameData.hTeam.teamId)
        )
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