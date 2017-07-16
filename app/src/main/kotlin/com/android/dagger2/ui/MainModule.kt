package com.android.dagger2.ui

import android.app.ProgressDialog
import android.content.Context
import com.android.dagger2.data.RepositoryImpl
import dagger.Module
import dagger.Provides

@Module
class MainModule(val context: Context,
        val view: MainContract.View) {

    @Provides
    @MainScope
    fun providePresenter(repository: RepositoryImpl): MainContract.Presenter {
        return MainPresenter(repository, view)
    }

    @Provides
    @MainScope
    fun provideProgressDialog(): ProgressDialog {
        val dialog = ProgressDialog(context)
        dialog.isIndeterminate = true
        dialog.setTitle("Loading")
        return dialog
    }
}