package com.example.familyrecipes.domain.models

data class RecipeWithCategories(
    val recipe: Recipe,
    val categories: List<Category>,
)
