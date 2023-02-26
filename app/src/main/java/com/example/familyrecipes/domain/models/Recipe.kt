package com.example.familyrecipes.domain.models

import android.graphics.Bitmap
import com.example.familyrecipes.data.entities.CategoryEntity
import java.time.LocalTime

data class Recipe(
    val id: Int=0,
    val name: String,
    val image: Bitmap? = null,
    val preparingTime: LocalTime,
    val servings: Int = 0,
    val categories: List<CategoryEntity>,
    val ingredients: List<Ingredient>,
    val method: List<MethodStep>,
)


