package com.android.dagger2

import com.android.dagger2.data.RepositoryImpl
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        ApiModule::class,
        RepositoryModule::class))
interface AppComponent {
    fun repository(): RepositoryImpl
    fun logger(): MyLogger
}