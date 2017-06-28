package com.android.dagger2.ui

import com.android.dagger2.data.RepositoryImpl
import com.android.dagger2.schedulers.BaseSchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class MainModule(val repository: RepositoryImpl, val view: MainContract.View, val schedulerProvider: BaseSchedulerProvider) {

    @Provides
    @MainScope
    fun providePresenter() : MainContract.Presenter {
        return MainPresenter(repository, view, schedulerProvider)
    }

}