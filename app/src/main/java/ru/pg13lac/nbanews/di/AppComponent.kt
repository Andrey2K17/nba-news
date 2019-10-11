package ru.pg13lac.nbanews.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import ru.pg13lac.nbanews.App
import javax.inject.Singleton

@Component(
    modules = [
        AndroidInjectionModule::class,
        CommonModule::class,
        ViewModelModule::class,
        AppModule::class
    ]
)
@Singleton
interface AppComponent : AndroidInjector<App> {
    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>() {
        @BindsInstance
        abstract fun context(context: Context): Builder
    }
}