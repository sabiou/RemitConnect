package com.godi.remitconnect.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class RecipientEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val country: String,
    val phoneNumber: String,
    val mobile_wallet: String,
    val firstName: String,
    val lastName: String,
)