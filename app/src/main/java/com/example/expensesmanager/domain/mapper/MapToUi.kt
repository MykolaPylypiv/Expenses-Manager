package com.example.expensesmanager.domain.mapper

import com.example.expensesmanager.core.Mapper
import com.example.expensesmanager.domain.DateTime
import javax.inject.Inject

class MapToUi @Inject constructor() : Mapper<DateTime, String> {

    override fun map(data: DateTime): String {
        return data.listOfMonth[data.monthNumber - 1].name
    }
}