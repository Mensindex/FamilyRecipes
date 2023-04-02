package com.example.familyrecipes.data.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import com.example.familyrecipes.utils.CATEGORY_ID
import com.example.familyrecipes.utils.ID
import com.example.familyrecipes.utils.RECIPE_CATEGORY_TABLE_NAME
import com.example.familyrecipes.utils.RECIPE_ID

@Entity(
    tableName = RECIPE_CATEGORY_TABLE_NAME,
    indices = [Index(value = arrayOf(RECIPE_ID, CATEGORY_ID), unique = true)],
    primaryKeys = [RECIPE_ID, CATEGORY_ID],
    foreignKeys = [
        ForeignKey(
            entity = RecipeEntity::class,
            parentColumns = [ID],
            childColumns = [RECIPE_ID],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = [ID],
            childColumns = [CATEGORY_ID],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class RecipeCategoryCrossRef(
    @ColumnInfo(name = RECIPE_ID, index = true)
    val recipeId: Long,
    @ColumnInfo(name = CATEGORY_ID, index = true)
    val categoryId: Long,
)