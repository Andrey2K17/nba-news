package ru.pg13lac.nbanews.di

import dagger.Module
import dagger.Provides
import ru.pg13lac.nbanews.data.repository.GamesRepository
import ru.pg13lac.nbanews.domain.interactor.GamesInteractor

@Module
class InteractorModule {
    @Provides
    fun provideGameListInteractor(repository: GamesRepository): GamesInteractor {
        return GamesInteractor(repository)
    }
}