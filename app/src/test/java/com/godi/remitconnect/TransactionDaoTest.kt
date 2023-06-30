package com.godi.remitconnect

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.godi.remitconnect.data.db.RecipientEntity
import com.godi.remitconnect.data.db.RemitConnectDatabase
import com.godi.remitconnect.data.db.TransactionDao
import com.godi.remitconnect.data.db.TransactionEntity
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertNotNull
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class TransactionDaoTest {
    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Inject
    lateinit var database: RemitConnectDatabase
    lateinit var transactionDao: TransactionDao

    @Before
    fun setup() {
        hiltRule.inject()
        transactionDao = database.transactionDao()
    }

    @After
    fun teardown() {
        database.close()
    }

    @Test
    fun insertTransaction_Success() = runTest {

        val transaction = TransactionEntity(
            recipient = RecipientEntity(
                id = 1,
                country = "bj",
                firstName = "John",
                lastName = "Doe",
                mobile_wallet = "Wave",
                phoneNumber = "83450004"
            ),
            amount = "1000"
        )
        transactionDao.insertTransaction(transaction)

        val insertedTransaction = transactionDao.getTransactionById(transaction.id).firstOrNull()
        assertNotNull(insertedTransaction)
        assertEquals(transaction.recipient.firstName, insertedTransaction?.recipient?.firstName)
    }
}