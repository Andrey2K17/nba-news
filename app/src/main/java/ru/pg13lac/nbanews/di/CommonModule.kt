package ru.pg13lac.nbanews.di

import dagger.Module
import dagger.Provides
import ru.pg13lac.nbanews.data.service.ApiHolder
import ru.pg13lac.nbanews.presentation.ui.box_score.BoxScoreAdapter
import ru.pg13lac.nbanews.presentation.ui.game_list.GameListAdapter

@Module
class CommonModule {
    @Provides
    fun provideApiHolder(): ApiHolder = ApiHolder

    @Provides
    fun provideBoxScoreAdapter(): BoxScoreAdapter = BoxScoreAdapter()

    @Provides
    fun provideGameListAdapter(): GameListAdapter = GameListAdapter()
}