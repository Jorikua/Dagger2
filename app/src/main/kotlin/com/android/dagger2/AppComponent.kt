package com.android.dagger2

import com.android.dagger2.data.RepositoryImpl
import com.android.dagger2.schedulers.BaseSchedulerProvider
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        ApiModule::class,
        RepositoryModule::class,
        SchedulerModule::class))
interface AppComponent {
    fun repository(): RepositoryImpl
    fun schedulerProvider(): BaseSchedulerProvider
}