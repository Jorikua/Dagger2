package com.android.dagger2

import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(
        AppModule::class,
        ApiModule::class,
        RepositoryModule::class,
        SchedulerModule::class))
interface AppComponent