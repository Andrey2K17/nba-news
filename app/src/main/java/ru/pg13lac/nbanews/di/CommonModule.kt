package ru.pg13lac.nbanews.di

import dagger.Module
import dagger.Provides
import ru.pg13lac.nbanews.data.service.Api
import ru.pg13lac.nbanews.data.service.ApiHolder
import ru.pg13lac.nbanews.presentation.ui.box_score.BoxScoreFragment
import ru.pg13lac.nbanews.presentation.ui.summary.SummaryFragment
import javax.inject.Singleton

@Module
class CommonModule {
    @Singleton
    @Provides
    fun provideApiHolder(): Api = ApiHolder().api

    @Singleton
    @Provides
    fun providesSummaryFragment(): SummaryFragment = SummaryFragment()

    @Singleton
    @Provides
    fun providesBoxScoreFragment(): BoxScoreFragment = BoxScoreFragment()
}