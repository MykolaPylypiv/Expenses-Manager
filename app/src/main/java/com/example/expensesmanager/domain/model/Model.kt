package com.example.expensesmanager.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "operation")
data class Operation(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "uid") val uid: Int = 0,

    @ColumnInfo(name = "sum") var sum: Int = 0,

    @ColumnInfo(name = "name") var name: String = "",

    @ColumnInfo(name = "comment") var comment: String = "",

    @ColumnInfo(name = "category") var category: String = ""
)

data class Month(
    val name: String, val days: Int
)

data class Settings(
    var isEnglish: Boolean = true, var isDark: Boolean = true, var currency: String = "UAH",
)

data class Category(
    val name: String, val expense: Int, val percent: Int, val color: Color
)

data class CategoryIcon(val text: String, val icon: ImageVector)

data class OperationIcon(
    val icon: ImageVector, val name: String, val category: String, val income: Int
)
