package com.android.dagger2

import com.android.dagger2.ui.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(AppModule::class, ApiModule::class))
interface AppComponent {
    fun inject(mainActivity: MainActivity)
}