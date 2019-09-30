package ru.pg13lac.nbanews.di

import dagger.Module
import dagger.Provides
import ru.pg13lac.nbanews.data.repository.GameListRepository
import ru.pg13lac.nbanews.domain.interactor.GameListInteractor

@Module
class InteractorModule {
    @Provides
    fun provideGameListInteractor(repository: GameListRepository): GameListInteractor {
        return GameListInteractor(repository)
    }
}