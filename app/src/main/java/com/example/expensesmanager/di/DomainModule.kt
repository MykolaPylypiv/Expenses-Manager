package com.example.expensesmanager.di

import com.example.expensesmanager.core.Mapper
import com.example.expensesmanager.domain.Date
import com.example.expensesmanager.domain.mapper.MapToUi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideDate(): Date = Date.Base()

    @Provides
    fun provideMapper(): Mapper<Date, String> = MapToUi()
}
