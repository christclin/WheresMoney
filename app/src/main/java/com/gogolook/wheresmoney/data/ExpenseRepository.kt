package com.gogolook.wheresmoney.data

import java.util.Date

interface ExpenseRepository {
    fun getAll(date: Date): List<Expense>
    fun getAll(): List<Expense>
    fun get(id: Int): Expense?
    fun insert(expense: Expense): Long
    fun update(expense: Expense)
    fun delete(expense: Expense)
}