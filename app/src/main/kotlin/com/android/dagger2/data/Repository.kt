package com.android.dagger2.data

import com.android.dagger2.data.remote.ApiHelper
import com.android.dagger2.model.User
import io.reactivex.Single

class Repository(val apiHelper: ApiHelper): RepositoryImpl {
    override fun getUser(username: String): Single<User> {
        return apiHelper.getUser(username)
                .map { it.user }
    }
}