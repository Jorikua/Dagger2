package com.android.dagger2.data.remote

import com.android.dagger2.data.remote.response.UserResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path

interface ApiInterface {
    @GET("/users/{username}")
    fun getUser(@Path("username") username: String): Single<UserResponse>
}