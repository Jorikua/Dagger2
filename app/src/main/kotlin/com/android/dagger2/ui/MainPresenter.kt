package com.android.dagger2.ui

import com.android.dagger2.data.RepositoryImpl

class MainPresenter(val repository: RepositoryImpl,
                    val view: MainContract.View) : MainContract.Presenter {
    override fun getUser(userName: String) {

    }

    override fun unsubscribe() {

    }
}