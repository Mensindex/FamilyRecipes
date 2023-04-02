package com.example.familyrecipes.ui.screens.category_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.familyrecipes.data.database.room.RecipeListRepositoryImpl
import com.example.familyrecipes.data.database.room.RecipeListRoomDao
import com.example.familyrecipes.domain.GetCategoryListUseCase
import com.example.familyrecipes.domain.GetRecipesCountByCategoryIdsUseCase
import com.example.familyrecipes.domain.models.Category
import com.example.familyrecipes.utils.Result
import com.example.familyrecipes.utils.asResult
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class CategoryListViewModel(dao: RecipeListRoomDao) : ViewModel() {

    private val _categoryListUiState: MutableStateFlow<CategoryListUiState> =
        MutableStateFlow(CategoryListUiState())
    val categoryListUiState = _categoryListUiState.asStateFlow()
    private val getCategoryListUseCase = GetCategoryListUseCase(RecipeListRepositoryImpl(dao))
    private val getRecipeCountsByCategoryIdsUseCase =
        GetRecipesCountByCategoryIdsUseCase(RecipeListRepositoryImpl(dao))

    init {
        getCategories()
    }

    private fun getCategories() {
        viewModelScope.launch {
            getCategoryListUseCase().asResult().collect { result ->
                when (result) {
                    is Result.Error -> {
                        _categoryListUiState.update { categoryListUiState ->
                            categoryListUiState.copy(
                                error = result.exception?.message,
                                loading = false
                            )
                        }
                    }

                    is Result.Loading -> {
                        _categoryListUiState.update { categoryListUiState ->
                            categoryListUiState.copy(loading = true, error = null)
                        }
                    }

                    is Result.Success -> {
                        _categoryListUiState.update { categoryListUiState ->
                            categoryListUiState.copy(
                                error = null,
                                loading = false,
                                categories = result.data,
                            )
                        }
                        getRecipeCounts(result.data.map { it.id })
                    }
                }
            }
        }
    }

    private fun getRecipeCounts(categoryIds: List<Long>) {
        viewModelScope.launch {
            getRecipeCountsByCategoryIdsUseCase(categoryIds = categoryIds).asResult()
                .collect { result ->
                    when (result) {
                        is Result.Error -> {
                            _categoryListUiState.update { categoryListUiState ->
                                categoryListUiState.copy(
                                    error = result.exception?.message,
                                    loading = false
                                )
                            }
                        }

                        is Result.Loading -> {
                            _categoryListUiState.update { categoryListUiState ->
                                categoryListUiState.copy(loading = true, error = null)
                            }
                        }
                        is Result.Success -> {
                            _categoryListUiState.update { categoryListUiState ->
                                categoryListUiState.copy(
                                    error = null,
                                    loading = false,
                                    recipeCounts = result.data
                                )
                            }
                        }
                    }
                }
        }

    }

//    private fun getRecipesCountByCategory(categoryId: Long) {
//        viewModelScope.launch {
//            getRecipesCountByCategoryUseCase(categoryId = categoryId).asResult().collect { result ->
//                when (result) {
//                    is Result.Error -> {
//
//                    }
//                    is Result.Loading -> {
//
//                    }
//                    is Result.Success -> {
//                        _categoryListUiState.update { categoryListUiState ->
//                            categoryListUiState.copy(recipeCount = result.data.toInt())
//                        }
//                    }
//                }
//            }
//        }
//
//    }
}

data class CategoryListUiState(
    val categories: List<Category> = emptyList(),
    val recipeCounts: List<Int> = emptyList(),
    val loading: Boolean = false,
    val error: String? = null,
)

//sealed interface CategoryListUiState {
//    data class CategoryListUi(
//        val categories: List<Category> = emptyList(),
//        val recipeCount: Int = 0,
//    ) : CategoryListUiState
//
//    object Loading : CategoryListUiState
//    data class Error(val msg: String) : CategoryListUiState
//}