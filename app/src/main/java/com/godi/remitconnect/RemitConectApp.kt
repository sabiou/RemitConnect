package com.godi.remitconnect

import android.app.Application
import com.godi.remitconnect.data.db.AccountRepository
import com.godi.remitconnect.data.model.AccountEntity
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltAndroidApp
class RemitConnectApp : Application() {
    @Inject
    lateinit var accountRepository: AccountRepository

    override fun onCreate() {
        super.onCreate()
        initializeAccount()
    }

    /**
     * Initializes the account by checking if it exists and creating a default account entity
     * with default values if it doesn't exist.
     */
    private fun initializeAccount() {
        runBlocking {
            val accountFlow = accountRepository.getAccount()

            // Observe the account flow
            val accountJob = accountFlow.launchIn(this)

            // Check if the account exists
            val account = accountFlow.firstOrNull()

            if (account == null) {
                // Account does not exist, initialize with default values
                val defaultAccount = AccountEntity(
                    id = 1,
                    name = "Farouk",
                    amount = 30000.0
                )
                accountRepository.saveAccount(defaultAccount)
            }
            // Cancel the account flow job
            accountJob.cancel()
        }
    }
}