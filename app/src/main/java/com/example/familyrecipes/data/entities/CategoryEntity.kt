package com.example.familyrecipes.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.familyrecipes.data.entities.CategoryEntity.Companion.CATEGORY_TABLE_NAME
import com.example.familyrecipes.domain.models.Category

@Entity(tableName = CATEGORY_TABLE_NAME)
data class CategoryEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = -1,
    @ColumnInfo(name = "category_name")
    val name: String,
){
    companion object {
        const val CATEGORY_TABLE_NAME = "categories_table"
    }
}

fun CategoryEntity.toCategory(): Category {
    return Category(id = id, name = name)
}

fun Category.toCategoryEntity(): CategoryEntity {
    return CategoryEntity(id = id, name = name)
}