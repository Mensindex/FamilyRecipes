package com.example.familyrecipes.domain.models

import android.graphics.Bitmap
import android.net.Uri

data class RecipeImage(
    val uri: Uri,
    val bitmap: Bitmap
)
