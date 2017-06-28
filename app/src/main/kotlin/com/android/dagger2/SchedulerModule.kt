package com.android.dagger2

import com.android.dagger2.schedulers.BaseSchedulerProvider
import com.android.dagger2.schedulers.ImmediateSchedulerProvider
import com.android.dagger2.schedulers.SchedulerProvider
import dagger.Module
import dagger.Provides
import javax.inject.Named
import javax.inject.Singleton

@Module
class SchedulerModule {

    @Provides
    @Singleton
    @Named("immediate")
    fun provideImmediateSchedulerProvider(): BaseSchedulerProvider {
        return ImmediateSchedulerProvider()
    }

    @Provides
    @Singleton
    @Named("standard")
    fun provideSchedulerProvider() : BaseSchedulerProvider {
        return SchedulerProvider()
    }
}