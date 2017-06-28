package com.android.dagger2.data

import com.android.dagger2.model.User
import io.reactivex.Single

interface RepositoryImpl {

    fun getUser(username: String): Single<User>

}