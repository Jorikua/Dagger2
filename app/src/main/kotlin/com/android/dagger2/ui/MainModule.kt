package com.android.dagger2.ui

import com.android.dagger2.data.RepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class MainModule(val view: MainContract.View) {

    @Provides
    @MainScope
    fun providePresenter(repository: RepositoryImpl) : MainContract.Presenter {
            return MainPresenter(repository, view)
    }
}