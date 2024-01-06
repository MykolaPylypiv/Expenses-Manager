package com.example.expensesmanager.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.expensesmanager.data.database.room.OperationDao
import com.example.expensesmanager.domain.model.Operation
import com.example.expensesmanager.domain.model.Settings

@Database(entities = [Operation::class], version = 3)
abstract class AppDatabase : RoomDatabase() {
    abstract fun operationDao(): OperationDao
}