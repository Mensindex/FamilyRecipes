package com.example.familyrecipes.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.familyrecipes.domain.models.Category
import com.example.familyrecipes.utils.CATEGORY_TABLE_NAME
import com.example.familyrecipes.utils.ID

@Entity(tableName = CATEGORY_TABLE_NAME)
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = ID)
    val id: Long = 0,
    @ColumnInfo(name = "category_name")
    val name: String,
)

fun CategoryEntity.toCategory(): Category {
    return Category(id = id, name = name)
}

fun Category.toCategoryEntity(): CategoryEntity {
    return CategoryEntity(id = id, name = name)
}