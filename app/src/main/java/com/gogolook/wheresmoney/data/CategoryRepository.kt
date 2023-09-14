package com.gogolook.wheresmoney.data

interface CategoryRepository {
    fun getAll(): List<Category>
    fun get(id: Int): Category?
    fun insert(category: Category): Long
    fun update(category: Category)
    fun delete(category: Category)
}