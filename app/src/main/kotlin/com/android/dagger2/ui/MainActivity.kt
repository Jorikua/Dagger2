package com.android.dagger2.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.android.dagger2.app
import com.android.dagger2.model.User
import javax.inject.Inject

class MainActivity: AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerMainComponent.builder()
                .appComponent(app.appComponent)
                .mainModule(MainModule(this))
                .build().inject(this)
    }

    override fun showProgress() {

    }

    override fun hideProgress() {

    }

    override fun onGetUserSuccess(user: User) {

    }

    override fun onGetUserFailure() {

    }
}