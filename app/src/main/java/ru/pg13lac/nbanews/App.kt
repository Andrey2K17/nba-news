package ru.pg13lac.nbanews

import android.app.Application
import ru.pg13lac.nbanews.di.AppComponent
import ru.pg13lac.nbanews.di.DaggerAppComponent

class App : Application() {

    lateinit var component: AppComponent

    override fun onCreate() {
        super.onCreate()
        instance = this

        component = DaggerAppComponent.builder()
            .build()
    }

    companion object {
        lateinit var instance: App
    }
}