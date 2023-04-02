package com.example.familyrecipes.domain.models

import android.graphics.Bitmap
import java.time.LocalTime

data class Recipe(
    val id: Long = 0,
    val name: String,
    val image: Bitmap? = null,
    val preparingTime: LocalTime,
    val servings: Int,
//    val categories: List<CategoryEntity>,
    val ingredients: List<Ingredient>,
    val method: List<MethodStep>,
)


