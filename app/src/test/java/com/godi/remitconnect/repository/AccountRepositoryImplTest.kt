package com.godi.remitconnect.repository

import com.godi.remitconnect.data.db.AccountDao
import com.godi.remitconnect.data.db.AccountRepository
import com.godi.remitconnect.data.db.AccountRepositoryImpl
import com.godi.remitconnect.data.model.AccountEntity
import com.nhaarman.mockitokotlin2.argumentCaptor
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify

@Suppress("DEPRECATION")
@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class AccountRepositoryImplTest {

    @Mock
    private lateinit var accountDao: AccountDao

    private lateinit var accountRepository: AccountRepository

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        accountRepository = AccountRepositoryImpl(accountDao)
    }

    /**
     * Tests the account initializer when an account already exists.
     */
    @Test
    fun testInitializeAccount_AccountExists() = runTest {
        // Call the account initializer
        accountRepository.initializeAccount()

        /**
         * Create an argument captor to capture the argument passed to saveAccount method and
         * Verify that the saveAccount method is called and capture the argument passed to it
         */
        val captor = argumentCaptor<AccountEntity>()
        verify(accountDao).saveAccount(captor.capture())

        val savedAccount = captor.firstValue
        assertEquals(1, savedAccount.id)
        assertEquals("Farouk", savedAccount.name)
        assertEquals(30000.0, savedAccount.amount)
    }
}
