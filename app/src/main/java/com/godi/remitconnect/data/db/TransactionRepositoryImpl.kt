package com.godi.remitconnect.data.db

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject

class TransactionRepositoryImpl @Inject constructor(private val transactionDao: TransactionDao) : TransactionRepository {
    override suspend fun saveTransaction(transaction: TransactionEntity) {
        transactionDao.insertTransaction(transaction)
    }

    override fun getAllTransactions(): Flow<List<TransactionEntity>> {
        return transactionDao.getAllTransactions()
    }
}