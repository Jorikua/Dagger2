package com.android.dagger2.data.remote.parser

import com.android.dagger2.data.remote.response.UserResponse
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import java.lang.reflect.Type

class UserParser : JsonDeserializer<UserResponse> {
    override fun deserialize(json: JsonElement, typeOfT: Type?, context: JsonDeserializationContext?): UserResponse {
        val response = UserResponse()
        return response
    }
}