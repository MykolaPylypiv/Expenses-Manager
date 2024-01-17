package com.example.expensesmanager.data.database.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.expensesmanager.domain.model.Category
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {

    @Transaction
    @Query("SELECT * FROM categories")
    fun categories(): Flow<List<Category>>

    @Query("SELECT * FROM categories WHERE isCosts LIKE :isCosts")
    fun costsCategories(isCosts: Boolean = true): Flow<List<Category>>

    @Query("SELECT * FROM categories WHERE isCosts LIKE :isCosts")
    fun incomesCategories(isCosts: Boolean = false): Flow<List<Category>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(category: Category)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(category: Category)

    @Delete
    suspend fun delete(category: Category)

    @Query("DELETE FROM categories")
    suspend fun deleteAll()
}