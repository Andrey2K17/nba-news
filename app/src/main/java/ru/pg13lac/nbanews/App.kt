package ru.pg13lac.nbanews

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ru.pg13lac.nbanews.di.DaggerAppComponent

class App : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().context(this).create(this)
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    companion object {
        lateinit var instance: App
    }
}