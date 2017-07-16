package com.android.dagger2.ui

import com.android.dagger2.data.RepositoryImpl
import com.android.dagger2.model.User
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

class MainPresenter(val repository: RepositoryImpl,
                    val view: MainContract.View) : MainContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getUser(userName: String) {
        view.showProgress()
        compositeDisposable.add(repository.getUser(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<User>() {
                    override fun onSuccess(t: User) {
                        view.hideProgress()
                        view.onGetUserSuccess(t)
                    }

                    override fun onError(e: Throwable) {
                        view.hideProgress()
                        view.onGetUserFailure()
                    }
                }))
    }

    override fun unsubscribe() {
        compositeDisposable.clear()
    }
}