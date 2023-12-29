package com.example.expensesmanager.domain.mapper

import com.example.expensesmanager.core.Mapper
import com.example.expensesmanager.domain.Date
import javax.inject.Inject

class MapToUi @Inject constructor() : Mapper<Date, String> {

    override fun map(data: Date): String {
        return data.listOfMonth[data.monthNumber - 1]
    }
}