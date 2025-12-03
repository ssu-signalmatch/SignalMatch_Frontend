package com.example.signalmatch_frontend.data.remote

import com.example.signalmatch_frontend.data.model.response.InvestorProfileData
import com.example.signalmatch_frontend.data.model.response.StartupProfileData
import com.example.signalmatch_frontend.data.model.response.Mypage
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type


class MypageAdapter : JsonDeserializer<Mypage> {

    override fun deserialize(
        json: JsonElement,
        typeOfT: Type,
        context: JsonDeserializationContext
    ): Mypage {
        val obj: JsonObject = json.asJsonObject

        return when {
            obj.has("investorId") -> {
                context.deserialize(obj, InvestorProfileData::class.java)
            }

            obj.has("startupName") -> {
                context.deserialize(obj, StartupProfileData::class.java)
            }

            else -> {
                throw IllegalArgumentException("Unknown profile type: $obj")
            }
        }
    }
}