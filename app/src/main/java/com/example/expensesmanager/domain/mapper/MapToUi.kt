package com.example.expensesmanager.domain.mapper

import com.example.expensesmanager.core.Mapper
import com.example.expensesmanager.domain.model.MapToUiParameters
import javax.inject.Inject

class MapToUi @Inject constructor() : Mapper<MapToUiParameters, String> {

    override fun map(data: MapToUiParameters): String {
        return data.dateTime.listOfMonth(language = data.language)[data.dateTime.monthNumber - 1].name
    }
}