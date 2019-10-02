package ru.pg13lac.nbanews.di

import dagger.Component
import ru.pg13lac.nbanews.presentation.ui.box_score.BoxScoreFragment
import ru.pg13lac.nbanews.presentation.ui.game_list.GameListFragment

@Component(
    modules = [
        CommonModule::class,
        RepositoryModule::class,
        InteractorModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {

    //Fragments
    fun inject(fragment: GameListFragment)
    fun inject(fragment: BoxScoreFragment)
}