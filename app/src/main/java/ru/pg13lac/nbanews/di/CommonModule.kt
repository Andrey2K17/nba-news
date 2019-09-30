package ru.pg13lac.nbanews.di

import dagger.Module
import dagger.Provides
import ru.pg13lac.nbanews.data.service.ApiHolder

@Module
class CommonModule {
    @Provides
    fun provideApiHolder(): ApiHolder {
        return ApiHolder
    }
}