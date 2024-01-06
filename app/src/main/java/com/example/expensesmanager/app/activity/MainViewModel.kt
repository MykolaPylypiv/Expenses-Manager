package com.example.expensesmanager.app.activity

import androidx.lifecycle.ViewModel
import com.example.expensesmanager.data.store.StoreSettings
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    storeSettings: StoreSettings
) : ViewModel() {

    val settings = storeSettings.get()
}