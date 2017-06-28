package com.android.dagger2.ui

import com.android.dagger2.model.User
import com.unight.android.ui.BasePresenter

interface MainContract {

    interface View {
        fun showProgress()
        fun hideProgress()
        fun onGetUserSuccess(user: User)
        fun onGetUserFailure()
    }

    interface Presenter: BasePresenter {
        fun getUser(userName: String)
    }
}