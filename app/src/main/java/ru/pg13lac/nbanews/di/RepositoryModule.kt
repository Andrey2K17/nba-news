package ru.pg13lac.nbanews.di

import dagger.Module
import dagger.Provides
import ru.pg13lac.nbanews.data.repository.GamesRepository
import ru.pg13lac.nbanews.data.service.ApiHolder

@Module
class RepositoryModule {
    @Provides
    fun provideGameListRepository(apiHolder: ApiHolder): GamesRepository =
        GamesRepository(apiHolder.api)
}