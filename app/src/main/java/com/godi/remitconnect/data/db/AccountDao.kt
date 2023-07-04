package com.godi.remitconnect.data.db

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Update
import androidx.room.Upsert
import com.godi.remitconnect.data.model.AccountEntity
import kotlinx.coroutines.flow.Flow

@Dao
/**
 * Data Access Object (DAO) interface for the AccountEntity.
 */
interface AccountDao {

    /**
     * Retrieves the account from the database.
     *
     * @return A Flow emitting the AccountEntity object representing the account.
     */
    @Query("SELECT * FROM account LIMIT 1")
    fun getAccount(): Flow<AccountEntity>

    /**
     * Saves the account to the database.
     *
     * @param account The AccountEntity object to be saved.
     */
    @Upsert
    suspend fun saveAccount(account: AccountEntity)

    /**
     * Updates the account in the database.
     *
     * @param account The AccountEntity object to be updated.
     */
    @Update
    suspend fun updateAccount(account: AccountEntity)
}
