package com.godi.remitconnect.data.db

import com.godi.remitconnect.data.model.AccountEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * Implementation of the AccountRepository interface.
 * Handles the retrieval and manipulation of account data.
 *
 * @param accountDao The DAO interface for accessing account data from the database.
 */
class AccountRepositoryImpl @Inject constructor(
    private val accountDao: AccountDao
) : AccountRepository {

    /**
     * Retrieves the account as a Flow, allowing observation of changes.
     *
     * @return A Flow emitting the AccountEntity object representing the account.
     */
    override suspend fun getAccount(): Flow<AccountEntity> = accountDao.getAccount()

    /**
     * Saves the account to the repository.
     *
     * @param account The AccountEntity object to be saved.
     */
    override suspend fun saveAccount(account: AccountEntity) = accountDao.saveAccount(account)

    /**
     * Initializes the account by creating a default account entity and saving it to the repository.
     * This method is typically called when there is no existing account data.
     */
    override suspend fun initializeAccount() {
        val defaultAccount = AccountEntity(1, "Farouk", 30000.0)
        saveAccount(defaultAccount)
    }
}