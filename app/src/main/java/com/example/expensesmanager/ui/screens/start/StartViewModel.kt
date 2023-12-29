package com.example.expensesmanager.ui.screens.start

import androidx.lifecycle.ViewModel
import com.example.expensesmanager.core.Mapper
import com.example.expensesmanager.domain.Date
import com.example.expensesmanager.domain.model.DragAnchors
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StartViewModel @Inject constructor(
    mapper: Mapper<Date, String>, date: Date
) : ViewModel() {

    val textDate = mapper.map(date)

    fun rightSwipe(position: Int): Int {
        return when (position) {
            0 -> -1
            1 -> 0
            -1 -> 1
            else -> 0
        }
    }

    fun leftSwipe(position: Int): Int {
        return when (position) {
            0 -> 1
            1 -> -1
            -1 -> 0
            else -> 0
        }
    }
}