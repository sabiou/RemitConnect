package com.godi.remitconnect.data.db

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

/**
 * An Interface contract
 */
interface TransactionRepository {
    suspend fun saveTransaction(transaction: TransactionEntity)

    fun getAllTransactions(): Flow<List<TransactionEntity>>
}