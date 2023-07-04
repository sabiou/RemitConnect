package com.godi.remitconnect.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class representing a transaction.
 *
 * @param id The ID of the transaction (auto-generated).
 * @param country The country associated with the transaction.
 * @param phoneNumber The phone number associated with the transaction.
 * @param mobile_wallet The mobile wallet associated with the transaction.
 * @param firstName The first name associated with the transaction.
 * @param lastName The last name associated with the transaction.
 * @param amount The amount of the transaction.
 */
@Entity(tableName = "transactions")
data class TransactionEntity(
    /**
     * The ID of the transaction (auto-generated).
     */
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    /**
     * The country associated with the transaction.
     */
    val country: String? = "",

    /**
     * The phone number associated with the transaction.
     */
    val phoneNumber: String? = "",

    /**
     * The mobile wallet associated with the transaction.
     */
    val mobile_wallet: String? = "",

    /**
     * The first name associated with the transaction.
     */
    val firstName: String? = "",

    /**
     * The last name associated with the transaction.
     */
    val lastName: String? = "",

    /**
     * The amount of the transaction.
     */
    val amount: Double? = 0.0
)
