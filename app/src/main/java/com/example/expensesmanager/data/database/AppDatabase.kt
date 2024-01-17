package com.example.expensesmanager.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.expensesmanager.data.database.room.CategoryDao
import com.example.expensesmanager.data.database.room.OperationDao
import com.example.expensesmanager.domain.model.Category
import com.example.expensesmanager.domain.model.Operation

@Database(entities = [Operation::class, Category::class], version = 6)
abstract class AppDatabase : RoomDatabase() {
    abstract fun operationDao(): OperationDao

    abstract fun categoryDao(): CategoryDao
}