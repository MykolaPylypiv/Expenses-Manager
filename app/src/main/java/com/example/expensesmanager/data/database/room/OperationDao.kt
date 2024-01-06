package com.example.expensesmanager.data.database.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.expensesmanager.domain.model.Operation
import kotlinx.coroutines.flow.Flow

@Dao
interface OperationDao {

    @Transaction
    @Query("SELECT * FROM operation")
    fun operation(): Flow<List<Operation>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(operation: Operation)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(operation: Operation)

    @Delete
    suspend fun delete(operation: Operation)

    @Query("DELETE FROM operation")
    suspend fun deleteAll()
}