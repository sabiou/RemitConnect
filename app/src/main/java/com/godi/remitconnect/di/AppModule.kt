package com.godi.remitconnect.di

import com.godi.remitconnect.data.db.AccountDao
import com.godi.remitconnect.data.db.AccountRepository
import com.godi.remitconnect.data.db.AccountRepositoryImpl
import com.godi.remitconnect.data.db.RemitConnectDatabase
import com.godi.remitconnect.data.db.TransactionDao
import com.godi.remitconnect.data.db.TransactionRepository
import com.godi.remitconnect.data.db.TransactionRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Dagger Hilt module that provides dependencies for the application.
 */
@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    /**
     * Provides the TransactionDao instance.
     *
     * @param database The RemitConnectDatabase instance.
     * @return The TransactionDao instance.
     */
    @Provides
    fun provideTransactionDao(
        database: RemitConnectDatabase
    ): TransactionDao = database.transactionDao()

    /**
     * Provides the TransactionRepository instance.
     *
     * @param transactionDao The TransactionDao instance.
     * @param accountDao The AccountDao instance.
     * @return The TransactionRepository instance.
     */
    @Provides
    fun provideTransactionRepository(
        transactionDao: TransactionDao,
        accountDao: AccountDao
    ): TransactionRepository = TransactionRepositoryImpl(transactionDao, accountDao)

    /**
     * Provides the AccountDao instance.
     *
     * @param database The RemitConnectDatabase instance.
     * @return The AccountDao instance.
     */
    @Provides
    fun provideAccountDao(
        database: RemitConnectDatabase
    ): AccountDao = database.accountDao()

    /**
     * Provides the AccountRepository instance.
     *
     * @param accountDao The AccountDao instance.
     * @return The AccountRepository instance.
     */
    @Provides
    fun provideAccountRepository(
        accountDao: AccountDao
    ): AccountRepository = AccountRepositoryImpl(accountDao)
}