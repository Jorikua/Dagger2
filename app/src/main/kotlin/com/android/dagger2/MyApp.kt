package com.android.dagger2

import android.app.Application

class MyApp : Application() {

    lateinit var netComponent: NetComponent

    override fun onCreate() {
        super.onCreate()

        netComponent = DaggerNetComponent.builder()
                .appModule(AppModule(this))
                .netModule(NetModule())
                .build()
    }
}