package com.android.dagger2.data.remote

import com.android.dagger2.data.remote.response.UserResponse
import io.reactivex.Single

class ApiHelper(val apiInterface: ApiInterface) : ApiHelperImpl{
    override fun getUser(username: String): Single<UserResponse> {
        return apiInterface.getUser(username)
    }
}