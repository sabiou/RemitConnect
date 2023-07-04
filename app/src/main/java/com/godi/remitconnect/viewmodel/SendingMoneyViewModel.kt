package com.godi.remitconnect.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.godi.remitconnect.data.db.AccountRepository
import com.godi.remitconnect.data.db.TransactionEntity
import com.godi.remitconnect.data.db.TransactionRepository
import com.godi.remitconnect.data.model.AccountEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * ViewModel class for the SendingMoneyScreen.
 *
 * @param transactionRepository The repository for managing transaction data.
 * @param accountRepository The repository for managing account data.
 */
@HiltViewModel
class SendingMoneyViewModel @Inject constructor(
    private val transactionRepository: TransactionRepository,
    private val accountRepository: AccountRepository
) : ViewModel() {

    private val _account = MutableStateFlow<AccountEntity?>(null)
    val account: StateFlow<AccountEntity?> = _account

    init {
        viewModelScope.launch {
            // Retrieve the account from the account repository and update the _account StateFlow
            accountRepository.getAccount().collect { account ->
                _account.value = account
            }
        }
    }

    /**
     * Saves a transaction using the transaction repository.
     *
     * @param transaction The transaction to be saved.
     */
    fun saveTransaction(transaction: TransactionEntity) {
        viewModelScope.launch {
            transactionRepository.saveTransaction(transaction)
        }
    }

    /**
     * Retrieves all transactions from the transaction repository.
     *
     * @return A Flow emitting a list of TransactionEntity objects.
     */
    fun getAllTransactions(): Flow<List<TransactionEntity>> {
        return transactionRepository.getAllTransactions()
    }

    /**
     * Searches for transactions by name using the transaction repository.
     *
     * @param query The search query.
     * @return A Flow emitting a list of TransactionEntity objects matching the search query.
     */
    fun searchTransactionsByName(query: String): Flow<List<TransactionEntity>> {
        return transactionRepository.searchTransactionsByName(query)
    }
}
