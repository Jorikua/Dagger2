package com.android.dagger2.ui

import android.app.ProgressDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.android.dagger2.R
import com.android.dagger2.app
import com.android.dagger2.model.User
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.onClick
import javax.inject.Inject

class MainActivity: AppCompatActivity(), MainContract.View {

    @Inject
    lateinit var presenter: MainContract.Presenter

    @Inject
    lateinit var progressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDaggerDependency()
        setContentView(R.layout.activity_main)

        main_btn_find.onClick {
            presenter.getUser(main_edt.text.toString())
        }
    }

    private fun injectDaggerDependency() {
        DaggerMainComponent.builder()
                .appComponent(app.appComponent)
                .mainModule(MainModule(this, this))
                .build().inject(this)
    }

    override fun showProgress() {
        progressDialog.show()
    }

    override fun hideProgress() {
        if (progressDialog.isShowing) {
            progressDialog.hide()
        }
    }

    override fun onGetUserSuccess(user: User) {
        main_tv_main_text.text = "Login - ${user.login}\nUrl - ${user.url}"
    }

    override fun onGetUserFailure() {
        main_tv_main_text.text = "Nothing was found"
    }

    override fun onStop() {
        super.onStop()
        hideProgress()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.unsubscribe()
    }
}