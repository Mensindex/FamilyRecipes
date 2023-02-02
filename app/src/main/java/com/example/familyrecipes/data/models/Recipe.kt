package com.example.familyrecipes.data.models

import android.graphics.Bitmap
import java.time.LocalTime

data class Recipe(
    val name: String,
    val image: Bitmap? = null,
    val preparingTime: LocalTime,
    val servings: Int,
    val ingredients: List<Ingredient>,
    val method: List<MethodStep>,
    )
