package com.gogolook.wheresmoney.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    fun getAll(): List<Category>

    @Query("SELECT * FROM categories WHERE id = :id")
    fun get(id: Int): Category?

    @Insert(onConflict = androidx.room.OnConflictStrategy.REPLACE)
    fun insert(category: Category): Long

    @Update(onConflict = androidx.room.OnConflictStrategy.IGNORE)
    fun update(category: Category)

    @Delete
    fun delete(category: Category)
}