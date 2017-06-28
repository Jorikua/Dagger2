package com.android.dagger2.ui

import com.android.dagger2.AppComponent
import dagger.Component

@MainScope
@Component(dependencies = arrayOf(AppComponent::class), modules = arrayOf(MainModule::class))
interface MainComponent {
    fun inject(mainActivity: MainActivity)
}