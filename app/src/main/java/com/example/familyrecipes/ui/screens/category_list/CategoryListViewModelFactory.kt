package com.example.familyrecipes.ui.screens.category_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.familyrecipes.data.database.room.RecipeListRoomDao
import com.example.familyrecipes.utils.UNKNOWN_VIEW_MODEL

class CategoryListViewModelFactory(private val dao: RecipeListRoomDao) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CategoryListViewModel::class.java)) {
            return CategoryListViewModel(dao = dao) as T
        } else {
            throw IllegalArgumentException(UNKNOWN_VIEW_MODEL)
        }
    }
}