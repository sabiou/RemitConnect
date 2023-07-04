package com.godi.remitconnect.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.godi.remitconnect.data.model.AccountEntity

/**
 * Room database class for the Remit Connect app.
 */
@Database(entities = [TransactionEntity::class, AccountEntity::class], version = 5, exportSchema = false)
abstract class RemitConnectDatabase : RoomDatabase() {
    /**
     * Returns an instance of the TransactionDao.
     *
     * @return The TransactionDao instance.
     */
    abstract fun transactionDao(): TransactionDao

    /**
     * Returns an instance of the AccountDao.
     *
     * @return The AccountDao instance.
     */
    abstract fun accountDao(): AccountDao
}