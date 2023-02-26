package com.example.familyrecipes.domain.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class Category(
    val id: Int = -1,
    val name: String,
    val isChecked: MutableState<Boolean> = mutableStateOf(false),
)
