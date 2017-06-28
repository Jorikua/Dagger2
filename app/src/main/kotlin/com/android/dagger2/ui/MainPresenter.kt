package com.android.dagger2.ui

import com.android.dagger2.data.RepositoryImpl
import com.android.dagger2.schedulers.BaseSchedulerProvider

class MainPresenter(val repository: RepositoryImpl,
                    val view: MainContract.View,
                    val schedulerProvider: BaseSchedulerProvider) : MainContract.Presenter {
    override fun getUser(userName: String) {

    }

    override fun unsubscribe() {

    }
}