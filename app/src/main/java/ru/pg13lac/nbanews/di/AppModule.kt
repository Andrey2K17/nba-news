package ru.pg13lac.nbanews.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import ru.pg13lac.nbanews.presentation.ui.player_profile.PlayerProfileFragment
import ru.pg13lac.nbanews.presentation.ui.box_score.BoxScoreFragment
import ru.pg13lac.nbanews.presentation.ui.team_list.TeamListFragment
import ru.pg13lac.nbanews.presentation.ui.game_details.GameDetailsFragment
import ru.pg13lac.nbanews.presentation.ui.game_list.GameListFragment
import ru.pg13lac.nbanews.presentation.ui.main.MainActivity
import ru.pg13lac.nbanews.presentation.ui.more.MoreFragment
import ru.pg13lac.nbanews.presentation.ui.summary.SummaryFragment
import ru.pg13lac.nbanews.presentation.ui.team_profile.TeamProfileFragment
import ru.pg13lac.nbanews.presentation.ui.teams_table.TeamsTableFragment

@Module(includes = [AndroidSupportInjectionModule::class])
interface AppModule {
    @ContributesAndroidInjector
    fun mainActivityInjector(): MainActivity

    @ContributesAndroidInjector
    fun gameListFragmentInjector(): GameListFragment

    @ContributesAndroidInjector
    fun gameDetailsFragmentInjector(): GameDetailsFragment

    @ContributesAndroidInjector
    fun summaryFragmentInjector(): SummaryFragment

    @ContributesAndroidInjector
    fun boxScoreFragmentInjector(): BoxScoreFragment

    @ContributesAndroidInjector
    fun moreFragmentInjection(): MoreFragment

    @ContributesAndroidInjector
    fun teamsTableFragmentInjection(): TeamsTableFragment

    @ContributesAndroidInjector
    fun favoriteTeamFragmentInjection(): TeamListFragment

    @ContributesAndroidInjector
    fun teamProfileFragmentInjection(): TeamProfileFragment

    @ContributesAndroidInjector
    fun playerProfileFragmentInjection(): PlayerProfileFragment
}