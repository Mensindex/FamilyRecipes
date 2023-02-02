package com.example.familyrecipes.data.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Category(
    val name: String,
    val isChecked: MutableState<Boolean> = mutableStateOf(false),
)
