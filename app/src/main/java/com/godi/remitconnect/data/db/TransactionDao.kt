package com.godi.remitconnect.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.godi.remitconnect.components.Transaction
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow

@Dao
interface TransactionDao {
    @Query("SELECT * FROM transactions")
    fun getAllTransactions(): Flow<List<TransactionEntity>>

    @Query("SELECT * FROM transactions WHERE id = :id")
    fun getTransactionById(id: Long): Flow<TransactionEntity?>

    @Insert
    suspend fun insertTransaction(transaction: TransactionEntity)
    // other CRUD operations
}