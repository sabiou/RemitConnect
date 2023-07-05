package com.godi.remitconnect.di

import android.content.Context
import androidx.room.Room
import com.godi.remitconnect.data.db.RemitConnectDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Database Hilt module that provides db dependencies for the application.
 */
@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    /**
     * Provides the RemitConnectDatabase instance.
     *
     * @param context The application context.
     * @return The RemitConnectDatabase instance.
     */
    @Provides
    @Singleton
    fun provideRemitConnectDatabase(
        @ApplicationContext context: Context
    ): RemitConnectDatabase {
        return Room.databaseBuilder(
            context, RemitConnectDatabase::class.java,
            "remit_connect.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
}