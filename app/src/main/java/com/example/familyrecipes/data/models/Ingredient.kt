package com.example.familyrecipes.data.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Ingredient(
    val name: MutableState<String> = mutableStateOf(""),
)
