package com.example.familyrecipes.ui.screens.adding_a_recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.familyrecipes.data.database.room.RecipeListRoomDao

class AddingARecipeViewModelFactory(private val dao: RecipeListRoomDao): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddingARecipeViewModel::class.java)) {
            return AddingARecipeViewModel(dao) as T
        }
        throw IllegalArgumentException("Unknown ViewModel")
    }
}