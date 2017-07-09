package com.android.dagger2

import android.app.Application

class MyApp : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        appComponent = DaggerAppComponent.builder()
                .repositoryModule(RepositoryModule())
                .apiModule(ApiModule())
                .appModule(AppModule(this))
                .build()
    }
}