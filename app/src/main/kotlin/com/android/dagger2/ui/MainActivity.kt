package com.android.dagger2.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.android.dagger2.app
import com.android.dagger2.data.RepositoryImpl
import com.android.dagger2.model.User
import com.android.dagger2.schedulers.BaseSchedulerProvider
import com.android.dagger2.schedulers.SchedulerProvider
import javax.inject.Inject
import javax.inject.Named

class MainActivity: AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    @Inject
    @Named("standard")
    lateinit var schedulerProvider: BaseSchedulerProvider

    @Inject
    lateinit var repository: RepositoryImpl

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        DaggerMainComponent.builder()
                .appComponent(app.appComponent)
                .mainModule(MainModule(repository, this, schedulerProvider))
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