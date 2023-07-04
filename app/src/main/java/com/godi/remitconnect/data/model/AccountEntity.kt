package com.godi.remitconnect.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "account")
data class AccountEntity(
    @PrimaryKey val id: Int,
    val name: String,
    val amount: Double
)