package ru.pg13lac.nbanews

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import ru.pg13lac.nbanews.di.DaggerAppComponent

class App : DaggerApplication() {

    override fun onCreate() {
        super.onCreate()
        instance = this
    }

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().context(this).create(this)
    }

    companion object {
        lateinit var instance: App
        const val API_KEY = "5v24mc82amrxkwa4uyd5bw9r"
    }
}