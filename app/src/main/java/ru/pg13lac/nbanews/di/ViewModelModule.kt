package ru.pg13lac.nbanews.di

import dagger.Module
import dagger.Provides
import ru.pg13lac.nbanews.domain.interactor.GamesInteractor
import ru.pg13lac.nbanews.presentation.viewModel.game_list.GameListViewModel
import ru.pg13lac.nbanews.presentation.viewModel.summary_game_details.SummaryGameDetailsViewModel
import javax.inject.Singleton

@Module
class ViewModelModule {
//    @Binds
//    abstract fun provideGameListViewModel(gameListViewModel: GameListViewModel): ViewModel
//
//    @Binds
//    abstract fun provideBoxScoreViewModel(boxScoreViewModel: BoxScoreViewModel): ViewModel
//
//    @Binds
//    @Singleton
//    abstract fun provideSummaryViewModel(gameDetailsViewModel: GameDetailsViewModel): ViewModel

    @Singleton
    @Provides
    fun provideGameListViewModel(interactor: GamesInteractor): GameListViewModel =
        GameListViewModel(interactor)

    @Singleton
    @Provides
    fun provideSummaryGameDetailsViewModel(interactor: GamesInteractor): SummaryGameDetailsViewModel =
        SummaryGameDetailsViewModel(interactor)
}