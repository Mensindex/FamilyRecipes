package com.example.familyrecipes.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.familyrecipes.data.testBase.TestDatabase
import com.example.familyrecipes.ui.screens.adding_a_recipe.AddingARecipe
import com.example.familyrecipes.ui.screens.category_list.CategoryListScreen
import com.example.familyrecipes.ui.screens.main_screen.MainScreen
import com.example.familyrecipes.ui.screens.recipe.RecipeScreen
import com.example.familyrecipes.ui.screens.recipe_list.RecipeList

sealed class NavRoute(val route: String) {
    object MainRoute : NavRoute(route = "Main screen")
    object CategoryListRoute : NavRoute(route = "Category list screen")
    object RecipeListRoute : NavRoute(route = "Recipe list screen")
    object AddingARecipeRoute : NavRoute(route = "Adding a recipe screen")
    object RecipeRoute : NavRoute(route = "Recipe screen")
}

@Composable
fun AppNavHost() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = NavRoute.MainRoute.route,
        builder = {
            composable(
                route = NavRoute.MainRoute.route,
                content = {
                    MainScreen(
                        navController = navController,
                        recipeList = TestDatabase.myRecipeList
                    )
                })
            composable(
                route = NavRoute.CategoryListRoute.route,
                content = { CategoryListScreen(navController = navController) })
            composable(
                route = NavRoute.RecipeListRoute.route,
                content = { RecipeList(
                    navController = navController,
                    recipeList = TestDatabase.myRecipeList,
                ) })
            composable(
                route = NavRoute.AddingARecipeRoute.route,
                content = {
                    AddingARecipe(
                        navController = navController,
                        onBackClick = {},
                        onCompleteClick = {}
                    )
                })
            composable(
                route = NavRoute.RecipeRoute.route,
                content = {
                    RecipeScreen(
                        navController = navController,
                        recipe = TestDatabase.myRecipeList[0],
                        onBackClick = {}
                    )
                })
        })
}