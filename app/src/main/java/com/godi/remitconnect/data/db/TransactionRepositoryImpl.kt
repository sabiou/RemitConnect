package com.godi.remitconnect.data.db

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

/**
 * Implementation of the [TransactionRepository] interface.
 *
 * @property transactionDao The DAO for managing transaction data.
 * @property accountDao The DAO for managing account data.
 */
class TransactionRepositoryImpl @Inject constructor(
    private val transactionDao: TransactionDao,
    private val accountDao: AccountDao
) : TransactionRepository {
    /**
     * Saves a transaction to the repository.
     *
     * @param transaction The transaction to be saved.
     */
    override suspend fun saveTransaction(transaction: TransactionEntity) {
        val currentAccount = accountDao.getAccount().first()
        currentAccount.let {
            val updatedAccount = currentAccount.copy(amount = it.amount - transaction.amount!!)
            accountDao.saveAccount(updatedAccount)
            transactionDao.insertTransaction(transaction)
        }
    }

    /**
     * Retrieves all transactions from the repository.
     *
     * @return A flow of lists of transactions.
     */
    override fun getAllTransactions(): Flow<List<TransactionEntity>> =
        transactionDao.getAllTransactions()

    /**
     * Searches for transactions by name in the repository.
     *
     * @param query The search query.
     * @return A flow of lists of transactions matching the search query.
     */
    override fun searchTransactionsByName(query: String): Flow<List<TransactionEntity>> =
        transactionDao.searchTransactionsByName(query)
}