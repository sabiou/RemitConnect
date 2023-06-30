package com.godi.remitconnect.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godi.remitconnect.data.db.TransactionEntity
import com.godi.remitconnect.data.db.TransactionRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SendingMoneyViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository
) : ViewModel() {

    fun saveTransaction(transaction: TransactionEntity) {
        viewModelScope.launch {
            transactionRepository.saveTransaction(transaction)
        }
    }

    fun getAllTransactions(): Flow<List<TransactionEntity>> {
        return transactionRepository.getAllTransactions()
    }
}
