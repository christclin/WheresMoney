package com.gogolook.wheresmoney.di

import android.content.Context
import androidx.room.Room
import com.gogolook.wheresmoney.data.CategoryDao
import com.gogolook.wheresmoney.data.CategoryRepository
import com.gogolook.wheresmoney.data.CategoryRepositoryImpl
import com.gogolook.wheresmoney.data.ExpenseDao
import com.gogolook.wheresmoney.data.ExpenseRepository
import com.gogolook.wheresmoney.data.ExpenseRepositoryImpl
import com.gogolook.wheresmoney.data.LedgerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {
    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): LedgerDatabase {
        return Room
            .databaseBuilder(
                context.applicationContext,
                LedgerDatabase::class.java,
                "ledger.db"
            )
            //.addMigrations()
            .build()
    }

    @Singleton
    @Provides
    fun provideExpenseDao(database: LedgerDatabase) = database.expenseDao()

    @Singleton
    @Provides
    fun provideCategoryDao(database: LedgerDatabase) = database.categoryDao()

    @Singleton
    @Provides
    fun provideCategoryRepository(dao: CategoryDao): CategoryRepository = CategoryRepositoryImpl(dao)

    @Singleton
    @Provides
    fun provideExpenseRepository(dao: ExpenseDao): ExpenseRepository = ExpenseRepositoryImpl(dao)
}