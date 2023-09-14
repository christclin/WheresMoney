package com.gogolook.wheresmoney.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import java.util.Date

@Dao
interface ExpenseDao {
    @Query("""
        SELECT * FROM expenses t
        INNER JOIN categories c ON t.category_id = c.id
        WHERE DATE(t.date / 1000, 'unixepoch') = DATE(:date / 1000, 'unixepoch')
    """)
    fun getAll(date: Date): Map<Expense, List<Category>>

    @Query("""
        SELECT * FROM expenses t
        INNER JOIN categories c ON t.category_id = c.id
    """)
    fun getAll(): Map<Expense, List<Category>>

    @Query("""
        SELECT * FROM expenses t
        INNER JOIN categories c ON t.category_id = c.id
        WHERE t.id = :id
    """)
    fun get(id: Int): Map<Expense, List<Category>>?

    @Insert
    fun insert(expense: Expense): Long

    @Update
    fun update(expense: Expense)

    @Delete
    fun delete(expense: Expense)
}