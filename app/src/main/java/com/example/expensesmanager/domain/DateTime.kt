package com.example.expensesmanager.domain

import android.annotation.SuppressLint
import com.example.expensesmanager.app.Language
import com.example.expensesmanager.domain.model.Month
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

interface DateTime {

    val nowDate: List<String>

    val monthNumber: Int

    val year: String

    fun listOfMonth(language: Language): List<Month>

    fun month(year: String, month: Int, language: Language): Month

    class Base @Inject constructor() : DateTime {
        @SuppressLint("SimpleDateFormat")
        override val nowDate = SimpleDateFormat("u/dd/M/yyyy/HH/mm").format(Date()).split("/")

        override val monthNumber = nowDate[2].toInt()
        override val year: String = nowDate[3]

        override fun listOfMonth(language: Language) = listOf(
            Month(name = language.months[0], days = 31),
            Month(name = language.months[1], days = 28),
            Month(name = language.months[2], days = 31),
            Month(name = language.months[3], days = 30),
            Month(name = language.months[4], days = 31),
            Month(name = language.months[5], days = 30),
            Month(name = language.months[6], days = 31),
            Month(name = language.months[7], days = 31),
            Month(name = language.months[8], days = 30),
            Month(name = language.months[9], days = 31),
            Month(name = language.months[10], days = 30),
            Month(name = language.months[11], days = 31)
        )

        override fun month(year: String, month: Int, language: Language): Month = if (year.toInt() % 4 == 0 && month == 1) {
            Month(name = "February", days = 29)
        } else if (month == -1) {
            listOfMonth(language = language)[11]
        } else listOfMonth(language = language)[month]
    }
}