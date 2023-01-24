package com.example.familyrecipes.data.models

import android.graphics.Bitmap

data class Recipe(
    val name: String,
    val photo: Bitmap,
    val preparingTime: String,
    val servings: Int,
    val ingredient: List<Ingredient>,
    val method: List<MethodStep>,
    )
