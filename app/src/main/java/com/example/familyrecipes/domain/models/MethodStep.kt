package com.example.familyrecipes.domain.models

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class MethodStep(
    val step: MutableState<String> = mutableStateOf("")
)