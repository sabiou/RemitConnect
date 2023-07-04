package com.godi.remitconnect.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object (DAO) interface for the TransactionEntity class.
 */
@Dao
interface TransactionDao {
    /**
     * Retrieves all transactions from the database.
     *
     * @return A Flow emitting a list of TransactionEntity objects representing all transactions.
     */
    @Query("SELECT * FROM transactions")
    fun getAllTransactions(): Flow<List<TransactionEntity>>

    /**
     * Retrieves a transaction by its ID.
     *
     * @param id The ID of the transaction.
     * @return A Flow emitting a single TransactionEntity object representing the transaction with the given ID.
     */
    @Query("SELECT * FROM transactions WHERE id = :id")
    fun getTransactionById(id: Long): Flow<TransactionEntity>

    /**
     * Inserts a new transaction into the database or updates an existing one.
     *
     * @param transaction The TransactionEntity object to be inserted or updated.
     */
    @Upsert
    suspend fun insertTransaction(transaction: TransactionEntity)

    /**
     * Deletes a transaction from the database.
     *
     * @param transaction The TransactionEntity object to be deleted.
     */
    @Delete
    suspend fun deleteTransaction(transaction: TransactionEntity)

    /**
     * Searches transactions by the specified search query.
     *
     * @param searchQuery The search query to match against the first name or last name of transactions.
     * @return A Flow emitting a list of TransactionEntity objects matching the search query.
     */
    @Query("SELECT * FROM transactions WHERE firstName LIKE '%' || :searchQuery || '%' OR lastName LIKE '%' || :searchQuery || '%'")
    fun searchTransactionsByName(searchQuery: String): Flow<List<TransactionEntity>>
}