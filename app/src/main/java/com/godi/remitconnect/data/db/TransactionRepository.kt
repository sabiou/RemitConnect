package com.godi.remitconnect.data.db

import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing transactions.
 */
interface TransactionRepository {
    /**
     * Saves a transaction to the repository.
     *
     * @param transaction The transaction to be saved.
     */
    suspend fun saveTransaction(transaction: TransactionEntity)

    /**
     * Retrieves all transactions from the repository.
     *
     * @return A flow of lists of transactions.
     */
    fun getAllTransactions(): Flow<List<TransactionEntity>>

    /**
     * Searches for transactions by name in the repository.
     *
     * @param query The search query.
     * @return A flow of lists of transactions matching the search query.
     */
    fun searchTransactionsByName(query: String): Flow<List<TransactionEntity>>
}
