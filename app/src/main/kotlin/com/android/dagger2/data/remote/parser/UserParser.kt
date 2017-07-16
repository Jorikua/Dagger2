package com.android.dagger2.data.remote.parser

import com.android.dagger2.data.remote.response.UserResponse
import com.android.dagger2.getLong
import com.android.dagger2.getString
import com.android.dagger2.model.User
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class UserParser : JsonDeserializer<UserResponse> {
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): UserResponse {
        val response = UserResponse()

        val jsonObject = json.asJsonObject

        val user = User()
        user.login = jsonObject.getString("login")
        user.avatarUrl = jsonObject.getString("avatar_url")
        user.id = jsonObject.getLong("id") ?: 0
        user.url = jsonObject.getString("url")

        response.user = user

        return response
    }
}