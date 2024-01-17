package com.example.expensesmanager.di

import android.content.Context
import androidx.room.Room
import com.example.expensesmanager.data.database.AppDatabase
import com.example.expensesmanager.data.database.room.CategoryDao
import com.example.expensesmanager.data.database.room.OperationDao
import com.example.expensesmanager.data.repository.CategoryRepository
import com.example.expensesmanager.data.repository.OperationRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private const val DATABASE_NAME = "database"

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideOperationDao(db: AppDatabase): OperationDao {
        return db.operationDao()
    }

    @Provides
    @Singleton
    fun provideCategoryDao(db: AppDatabase): CategoryDao {
        return db.categoryDao()
    }

    @Provides
    @Singleton
    fun provideOperationRepository(dao: OperationDao): OperationRepository {
        return OperationRepository.Base(dao)
    }

    @Provides
    @Singleton
    fun provideCategoryRepository(dao: CategoryDao): CategoryRepository {
        return CategoryRepository.Base(dao)
    }
}