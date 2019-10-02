package ru.pg13lac.nbanews.di

import dagger.Module
import dagger.Provides
import ru.pg13lac.nbanews.domain.interactor.GamesInteractor
import ru.pg13lac.nbanews.presentation.viewModel.box_score.BoxScoreViewModel
import ru.pg13lac.nbanews.presentation.viewModel.game_list.GameListViewModel

@Module
class ViewModelModule {
    @Provides
    fun provideGameListViewModel(interactor: GamesInteractor): GameListViewModel =
        GameListViewModel(interactor)

    @Provides
    fun provideBoxScoreViewModel(interactor: GamesInteractor): BoxScoreViewModel =
        BoxScoreViewModel(interactor)
}