package com.android.dagger2.ui

import android.app.Application
import android.util.Log
import javax.inject.Inject

@MainScope
class MyLogger @Inject constructor() {

    fun log(logMessage: String) {
        Log.d("TAG", "Sevka: $logMessage")
    }
}