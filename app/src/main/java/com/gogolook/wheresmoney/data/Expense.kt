package com.gogolook.wheresmoney.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.Date

@Entity(
    tableName = "expenses",
    foreignKeys = [
        androidx.room.ForeignKey(
            entity = Category::class,
            parentColumns = ["id"],
            childColumns = ["category_id"],
            onDelete = androidx.room.ForeignKey.CASCADE
        )
    ],
    indices = [Index(value = ["category_id"])]
)
data class Expense(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val amount: Int,

    @ColumnInfo(name = "category_id")
    val categoryId: Int,

    @ColumnInfo(defaultValue = "CURRENT_TIMESTAMP")
    val date: Date = Date()
) {
    @Ignore
    var category: Category? = null
}
