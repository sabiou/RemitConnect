package com.godi.remitconnect.data.db

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import org.json.JSONObject

@ProvidedTypeConverter
class RecipientTypeConverter {
    @TypeConverter
    fun fromDatabaseValue(value: String): RecipientEntity {
        val jsonObject = JSONObject(value)
        return RecipientEntity(
            country = jsonObject.getString("country"),
            id = jsonObject.getLong("id"),
            mobile_wallet = jsonObject.getString("mobile_wallet"),
            firstName = jsonObject.getString("firstName"),
            lastName = jsonObject.getString("lastName"),
            phoneNumber = jsonObject.getString("phoneNumber")
        )
    }

    @TypeConverter
    fun toDatabaseValue(recipient: RecipientEntity): String {
        val jsonObject = JSONObject()
        jsonObject.put("country", recipient.country)
        jsonObject.put("id", recipient.id)
        jsonObject.put("mobile_wallet", recipient.mobile_wallet)
        jsonObject.put("firstName", recipient.firstName)
        jsonObject.put("lastName", recipient.lastName)
        jsonObject.put("phoneNumber", recipient.phoneNumber)
        return jsonObject.toString()
    }
}
