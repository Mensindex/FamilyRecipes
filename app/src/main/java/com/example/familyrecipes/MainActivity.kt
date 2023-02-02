package com.example.familyrecipes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.familyrecipes.ui.navigation.AppNavHost
import com.example.familyrecipes.ui.theme.FamilyRecipesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FamilyRecipesTheme {
                AppNavHost()
            }
        }
    }
}