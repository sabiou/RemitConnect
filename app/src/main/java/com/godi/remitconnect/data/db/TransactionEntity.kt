package com.godi.remitconnect.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "transactions")
data class TransactionEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val recipient: RecipientEntity,
    val amount: String
)