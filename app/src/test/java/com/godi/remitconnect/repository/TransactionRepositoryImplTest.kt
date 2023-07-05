package com.godi.remitconnect.repository

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.godi.remitconnect.data.db.AccountDao
import com.godi.remitconnect.data.db.TransactionDao
import com.godi.remitconnect.data.db.TransactionEntity
import com.godi.remitconnect.data.db.TransactionRepository
import com.godi.remitconnect.data.db.TransactionRepositoryImpl
import com.godi.remitconnect.data.model.AccountEntity
import com.nhaarman.mockitokotlin2.argumentCaptor
import junit.framework.TestCase.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class TransactionRepositoryImplTest {

    @Mock
    private lateinit var transactionDao: TransactionDao

    @Mock
    private lateinit var accountDao: AccountDao

    private lateinit var transactionRepository: TransactionRepository


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        MockitoAnnotations.initMocks(this)
        transactionRepository = TransactionRepositoryImpl(transactionDao, accountDao)
    }

    @Test
    fun testSaveTransaction() = runTest {
        // Mock data
        val transaction = TransactionEntity(
            id = 1,
            country = "+229",
            phoneNumber = "123456789",
            mobile_wallet = "MTN Mobile Money",
            firstName = "John",
            lastName = "Doe",
            amount = 1000.0
        )
        val currentAccount = AccountEntity(id = 1, name = "Farouk", amount = 50000.0)

        // Mock dependencies' behavior
        Mockito.`when`(accountDao.getAccount()).thenReturn(flowOf(currentAccount))

        // Capture the argument passed to saveAccount
        val accountCapture = argumentCaptor<AccountEntity>()

        // Call the method
        transactionRepository.saveTransaction(transaction)

        // Verify the interactions
        verify(accountDao).getAccount()
        verify(accountDao).saveAccount(accountCapture.capture())

        // Verify the captured account entity
        val updatedAccount = accountCapture.firstValue
        assertEquals(49000.0, updatedAccount.amount)
        assertEquals("Farouk", updatedAccount.name)

        // verify transaction insertion
        verify(transactionDao).insertTransaction(transaction)
    }

}