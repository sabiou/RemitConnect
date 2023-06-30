package com.godi.remitconnect.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [TransactionEntity::class], version = 1, exportSchema = false)
@TypeConverters(RecipientTypeConverter::class)
abstract class RemitConnectDatabase: RoomDatabase() {
    abstract fun transactionDao(): TransactionDao
}