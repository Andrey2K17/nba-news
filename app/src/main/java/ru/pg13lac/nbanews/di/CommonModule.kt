package ru.pg13lac.nbanews.di

import dagger.Module
import dagger.Provides
import ru.pg13lac.nbanews.data.service.Api
import ru.pg13lac.nbanews.data.service.ApiHolder
import javax.inject.Singleton

@Module
class CommonModule {
    @Singleton
    @Provides
    fun provideApiHolder(): Api = ApiHolder().api
}