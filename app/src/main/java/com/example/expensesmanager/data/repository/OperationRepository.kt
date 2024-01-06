package com.example.expensesmanager.data.repository

import com.example.expensesmanager.data.database.room.OperationDao
import com.example.expensesmanager.domain.model.Operation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface OperationRepository {

    fun operation(): Flow<List<Operation>>

    suspend fun insert(operation: Operation)

    suspend fun delete(operation: Operation)

    suspend fun update(operation: Operation)

    suspend fun deleteAll()

    class Base @Inject constructor(private val dao: OperationDao) : OperationRepository {

        override fun operation() = dao.operation()

        override suspend fun insert(operation: Operation) = dao.insert(operation)

        override suspend fun delete(operation: Operation) = dao.delete(operation)

        override suspend fun update(operation: Operation) = dao.update(operation)

        override suspend fun deleteAll() = dao.deleteAll()
    }
}