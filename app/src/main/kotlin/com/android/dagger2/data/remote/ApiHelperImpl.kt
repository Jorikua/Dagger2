package com.android.dagger2.data.remote

import com.android.dagger2.data.remote.response.UserResponse
import io.reactivex.Single

interface ApiHelperImpl {

    fun getUser(username: String): Single<UserResponse>
}