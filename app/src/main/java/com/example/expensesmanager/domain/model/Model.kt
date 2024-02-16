package com.example.expensesmanager.domain.model

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.DateTime

@Entity(tableName = "operation")
data class Operation(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "uid") val uid: Int = 0,

    @ColumnInfo(name = "sum") var sum: Int = 0,

    @ColumnInfo(name = "name") var name: String = "",

    @ColumnInfo(name = "comment") var comment: String = "",

    @ColumnInfo(name = "category") var category: String = ""
)

@Entity(tableName = "categories")
data class Category(
    @PrimaryKey(autoGenerate = true) @ColumnInfo(name = "uid") val uid: Int = 0,

    @ColumnInfo(name = "name") var name: String = "",

    @ColumnInfo(name = "iconId") var iconId: Int = 0,

    @ColumnInfo(name = "isCosts") var isCosts: Boolean = true
)

data class CategoryOperation(
    val name: String, val expense: Int, val percent: Int, val color: Color
)

data class CategoryIcon(
    val name: String, val color: Color, val iconId: Int
)

data class AddCategory(
    val name: String, val color: Color, val sum: String
)

data class Month(
    val name: String, val days: Int
)

data class Settings(
    var isEnglish: Boolean = true, var isDark: Boolean = true, var currency: String = "UAH",
)

data class MapToUiParameters(val dateTime: DateTime, val language: Language)
