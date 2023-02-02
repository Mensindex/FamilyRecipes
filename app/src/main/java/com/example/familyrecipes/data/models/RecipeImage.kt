package com.example.familyrecipes.data.models

import android.graphics.Bitmap
import android.net.Uri

data class RecipeImage(
    val uri: Uri,
    val bitmap: Bitmap
)
