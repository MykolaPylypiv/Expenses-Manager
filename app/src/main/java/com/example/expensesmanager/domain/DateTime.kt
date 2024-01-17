package com.example.expensesmanager.domain

import android.annotation.SuppressLint
import com.example.expensesmanager.domain.model.Month
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

interface DateTime {
    val listOfMonth: List<Month>

    val nowDate: List<String>

    val monthNumber: Int

    val year: String

    fun month(year: String, month: Int): Month

    class Base @Inject constructor() : DateTime {
        override val listOfMonth = listOf(
            Month(name = "January", days = 31),
            Month(name = "February", days = 28),
            Month(name = "March", days = 31),
            Month(name = "April", days = 30),
            Month(name = "May", days = 31),
            Month(name = "June", days = 30),
            Month(name = "July", days = 31),
            Month(name = "August", days = 31),
            Month(name = "September", days = 30),
            Month(name = "October", days = 31),
            Month(name = "November", days = 30),
            Month(name = "December", days = 31)
        )

        @SuppressLint("SimpleDateFormat")
        override val nowDate = SimpleDateFormat("u/dd/M/yyyy/HH/mm").format(Date()).split("/")

        override val monthNumber = nowDate[2].toInt()
        override val year: String = nowDate[3]

        override fun month(year: String, month: Int): Month = if (year.toInt() % 4 == 0 && month == 1) {
            Month(name = "February", days = 29)
        } else if (month == -1) {
            listOfMonth[11]
        } else listOfMonth[month]
    }
}