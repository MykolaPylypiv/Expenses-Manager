package com.example.expensesmanager.core

interface Mapper<S, R> {

    fun map(data: S): R
}