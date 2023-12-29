package com.example.expensesmanager.domain

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Date
import javax.inject.Inject

interface Date {
    val listOfMonth: List<String>

    val nowDate: List<String>

    val monthNumber: Int

    fun month(monthNumber: Int): String

    class Base @Inject constructor() : com.example.expensesmanager.domain.Date {
        override val listOfMonth = listOf(
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
        )

        @SuppressLint("SimpleDateFormat")
        override val nowDate = SimpleDateFormat("u/dd/M/yyyy/HH/mm").format(Date()).split("/")

        override val monthNumber = nowDate[2].toInt()

        override fun month(monthNumber: Int) = listOfMonth[monthNumber - 1]
    }
}