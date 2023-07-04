package com.godi.remitconnect.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey

/**
 * Entity class representing a recipient.
 *
 * @param id The ID of the recipient entity (auto-generated).
 * @param country The country of the recipient.
 * @param phoneNumber The phone number of the recipient.
 * @param mobile_wallet The mobile wallet information of the recipient.
 * @param firstName The first name of the recipient.
 * @param lastName The last name of the recipient.
 */
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