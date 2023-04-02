package com.example.familyrecipes.ui.screens.recipe_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.familyrecipes.data.database.room.RecipeListRoomDao
import com.example.familyrecipes.utils.UNKNOWN_VIEW_MODEL

class RecipeListViewModelFactory(private val dao: RecipeListRoomDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(RecipeListViewModel::class.java)) {
            return RecipeListViewModel(dao = dao) as T
        } else {
            throw IllegalArgumentException(UNKNOWN_VIEW_MODEL)
        }
    }
}