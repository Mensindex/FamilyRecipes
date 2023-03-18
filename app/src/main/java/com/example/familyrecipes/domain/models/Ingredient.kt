package com.example.familyrecipes.domain.models

import androidx.compose.runtime.MutableState

data class Ingredient(
    var name: MutableState<String>,
)
