package com.example.familyrecipes

import android.graphics.BitmapFactory
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import com.example.familyrecipes.data.models.Ingredient
import com.example.familyrecipes.data.models.MethodStep
import com.example.familyrecipes.data.models.Recipe
import com.example.familyrecipes.ui.screens.recipe.RecipeScreen
import com.example.familyrecipes.ui.theme.FamilyRecipesTheme
import java.time.LocalTime

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FamilyRecipesTheme {
                // A surface container using the 'background' color from the theme
                RecipeScreen(
                    recipe = Recipe(
                        name = "Beef soup",
                        image = BitmapFactory.decodeResource(resources, R.drawable.placeholder),
                        preparingTime = LocalTime.of(1, 20),
                        servings = 4,
                        ingredients = mutableListOf(
                            Ingredient(name = remember { mutableStateOf("tomato") }),
                            Ingredient(name = remember { mutableStateOf("garlic") }),
                            Ingredient(name = remember { mutableStateOf("sugar") }),
                        ),
                        method = mutableListOf(
                            MethodStep(step = remember { mutableStateOf("Turn on gas") }),
                            MethodStep(step = remember { mutableStateOf("Broke three eggs") }),
                            MethodStep(step = remember { mutableStateOf("Slice a tomato") }),
                        )
                    ),
                    onBackClick = {}
                )
            }
        }
    }
}