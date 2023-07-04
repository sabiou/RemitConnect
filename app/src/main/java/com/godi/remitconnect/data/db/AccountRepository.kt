package com.godi.remitconnect.data.db

import com.godi.remitconnect.data.model.AccountEntity
import kotlinx.coroutines.flow.Flow

/**
 * Repository interface for managing account data.
 */
interface AccountRepository {

    /**
     * Retrieves the account as a Flow, allowing observation of changes.
     *
     * @return A Flow emitting the AccountEntity object representing the account.
     */
    suspend fun getAccount(): Flow<AccountEntity>

    /**
     * Saves the account to the repository.
     *
     * @param account The AccountEntity object to be saved.
     */
    suspend fun saveAccount(account: AccountEntity)

    /**
     * Initializes the account.
     * This method can be used to perform any necessary setup or initialization for the account.
     */
    suspend fun initializeAccount()
}