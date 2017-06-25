package com.android.dagger2.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.android.dagger2.app
import io.reactivex.Completable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.http.POST
import javax.inject.Inject

class MainActivity: AppCompatActivity() {

    @Inject lateinit var retrofit: Retrofit
    private lateinit var apiInterface: ApiInteface

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        app.appComponent.inject(this)

        apiInterface  = retrofit.create(ApiInteface::class.java)

        apiInterface.getUser()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                }, {

                })
    }

    interface ApiInteface {

        @POST("asd/asd")
        fun getUser() : Completable
    }
}