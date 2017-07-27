package com.android.dagger2

import android.util.Log
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MyLogger @Inject constructor() {

    fun log(logMessage: String) {
        Log.d("TAG", "Sevka: $logMessage")
    }
}