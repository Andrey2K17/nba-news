package ru.pg13lac.nbanews.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.pg13lac.nbanews.domain.interactor.GamesInteractor
import ru.pg13lac.nbanews.presentation.viewModel.box_score.BoxScoreViewModel
import ru.pg13lac.nbanews.presentation.viewModel.game_list.GameListViewModel
import ru.pg13lac.nbanews.presentation.viewModel.summary.GameDetailsViewModel
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

    @Provides
    fun provideGameListViewModel(interactor: GamesInteractor): GameListViewModel =
        GameListViewModel(interactor)

    @Provides
    fun provideBoxScoreViewModel(interactor: GamesInteractor): BoxScoreViewModel =
        BoxScoreViewModel(interactor)

    @Singleton
    @Provides
    fun provideSummaryViewModel(interactor: GamesInteractor): GameDetailsViewModel =
        GameDetailsViewModel(interactor)
}