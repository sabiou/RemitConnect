package com.godi.remitconnect.di

import android.content.Context
import androidx.room.Room
import com.godi.remitconnect.data.db.RecipientTypeConverter
import com.godi.remitconnect.data.db.RemitConnectDatabase
import com.godi.remitconnect.data.db.TransactionDao
import com.godi.remitconnect.data.db.TransactionRepository
import com.godi.remitconnect.data.db.TransactionRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideRemitConnectDatabase(@ApplicationContext context: Context): RemitConnectDatabase {
        return Room.databaseBuilder(
            context, RemitConnectDatabase::class.java,
            "remit_connect.db"
        )
            .fallbackToDestructiveMigration()
            .addTypeConverter(RecipientTypeConverter())
            .build()
    }

    @Provides
    fun provideTransactionDao(database: RemitConnectDatabase): TransactionDao {
        return database.transactionDao()
    }

    @Provides
    fun provideTransactionRepository(transactionDao: TransactionDao): TransactionRepository {
        return TransactionRepositoryImpl(transactionDao)
    }
}