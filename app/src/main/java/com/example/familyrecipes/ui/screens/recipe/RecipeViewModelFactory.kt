package com.example.familyrecipes.ui.screens.recipe

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.familyrecipes.data.database.room.RecipeListRoomDao
import com.example.familyrecipes.utils.UNKNOWN_VIEW_MODEL

class RecipeViewModelFactory(private val dao: RecipeListRoomDao) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeViewModel::class.java)) {
            return RecipeViewModel(dao = dao) as T
        }
        throw IllegalArgumentException(UNKNOWN_VIEW_MODEL)
    }
}