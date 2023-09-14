package com.gogolook.wheresmoney.data

import java.util.Date

class ExpenseRepositoryImpl(private val dao: ExpenseDao) : ExpenseRepository {
    override fun getAll(date: Date): List<Expense> = dao.getAll(date).map { (key, value) ->
        key.category = value[0]
        key
    }

    override fun getAll(): List<Expense> = dao.getAll().map { (key, value) ->
        key.category = value[0]
        key
    }

    override fun get(id: Int): Expense? = dao.get(id)?.map { (key, value) ->
        key.category = value[0]
        key
    }?.get(0)

    override fun insert(expense: Expense): Long = dao.insert(expense)
    override fun update(expense: Expense) = dao.update(expense)
    override fun delete(expense: Expense) = dao.delete(expense)
}