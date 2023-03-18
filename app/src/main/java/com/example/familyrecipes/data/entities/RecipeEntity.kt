package com.example.familyrecipes.data.entities

import android.graphics.Bitmap
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.familyrecipes.data.entities.RecipeEntity.Companion.RECIPE_TABLE_NAME
import com.example.familyrecipes.domain.models.Ingredient
import com.example.familyrecipes.domain.models.MethodStep
import com.example.familyrecipes.domain.models.Recipe
import java.time.LocalTime

@Entity(tableName = RECIPE_TABLE_NAME)
data class RecipeEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "recipe_name")
    val name: String,
    @ColumnInfo(name = "recipe_image")
    val image: Bitmap? = null,
    @ColumnInfo(name = "preparing_time")
    val preparingTime: LocalTime,
    @ColumnInfo(name = "servings")
    val servings: Int,
    @ColumnInfo(name = "recipe_categories")
    val categories: List<CategoryEntity>,
    @ColumnInfo(name = "ingredients")
    val ingredients: List<Ingredient>,
    @ColumnInfo(name = "method_steps")
    val method: List<MethodStep>,
) {
    companion object {
        const val RECIPE_TABLE_NAME = "recipes_table"
    }
}

fun RecipeEntity.toRecipe(): Recipe {
    return Recipe(
        id = id,
        name = name,
        image = image,
        preparingTime = preparingTime,
        servings = servings,
        categories = categories,
        ingredients = ingredients,
        method = method,
    )
}

fun Recipe.toRecipeEntity(): RecipeEntity {
    return RecipeEntity(
        id = id,
        name = name,
        image = image,
        preparingTime = preparingTime,
        servings = servings,
        categories = categories,
        ingredients = ingredients ,
        method = method,
    )
}


