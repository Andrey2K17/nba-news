package ru.pg13lac.nbanews.di

import dagger.Module
import dagger.Provides
import ru.pg13lac.nbanews.data.repository.GameListRepository
import ru.pg13lac.nbanews.data.service.ApiHolder

@Module
class RepositoryModule {
    @Provides
    fun provideGameListRepository(apiHolder: ApiHolder): GameListRepository {
        return GameListRepository(apiHolder.api)
    }
}