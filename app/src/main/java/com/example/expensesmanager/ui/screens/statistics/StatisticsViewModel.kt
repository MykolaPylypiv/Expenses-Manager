package com.example.expensesmanager.ui.screens.statistics

import androidx.lifecycle.ViewModel
import com.example.expensesmanager.core.Mapper
import com.example.expensesmanager.domain.Date
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StatisticsViewModel @Inject constructor(
    mapper: Mapper<Date, String>, date: Date
) : ViewModel() {

    val textDate = mapper.map(date)
}