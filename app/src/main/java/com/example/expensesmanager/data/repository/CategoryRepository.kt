package com.example.expensesmanager.data.repository

import com.example.expensesmanager.data.database.room.CategoryDao
import com.example.expensesmanager.data.database.room.OperationDao
import com.example.expensesmanager.domain.model.Category
import com.example.expensesmanager.domain.model.Operation
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

interface CategoryRepository {

    fun categories(): Flow<List<Category>>

    fun costsCategories(): Flow<List<Category>>

    fun incomesCategories(): Flow<List<Category>>

    suspend fun insert(category: Category)

    suspend fun update(category: Category)

    suspend fun delete(category: Category)

    suspend fun deleteAll()

    class Base @Inject constructor(private val dao: CategoryDao) : CategoryRepository {

        override fun categories() = dao.categories()

        override fun costsCategories() = dao.costsCategories()

        override fun incomesCategories() = dao.incomesCategories()

        override suspend fun insert(category: Category) = dao.insert(category)

        override suspend fun delete(category: Category) = dao.delete(category)

        override suspend fun update(category: Category) = dao.update(category)

        override suspend fun deleteAll() = dao.deleteAll()
    }
}