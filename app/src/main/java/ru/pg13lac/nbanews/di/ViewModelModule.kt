package ru.pg13lac.nbanews.di

import dagger.Module
import dagger.Provides
import ru.pg13lac.nbanews.domain.interactor.GamesInteractor
import ru.pg13lac.nbanews.presentation.viewModel.game_list.GameListViewModel

@Module
class ViewModelModule {
    @Provides
    fun provideGameListViewModel(interactor: GamesInteractor): GameListViewModel {
        return GameListViewModel(interactor)
    }
}