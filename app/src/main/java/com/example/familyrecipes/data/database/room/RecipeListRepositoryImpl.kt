package com.example.familyrecipes.data.database.room

import com.example.familyrecipes.data.entities.CategoryEntity
import com.example.familyrecipes.data.entities.toCategory
import com.example.familyrecipes.domain.*
import com.example.familyrecipes.domain.models.Category
import com.example.familyrecipes.domain.models.Recipe
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecipeListRepositoryImpl(private val recipeListRoomDao: RecipeListRoomDao) :
    RecipeListRepository {

//    private val recipeListDao = AppRoomDatabase.getInstance()

    override suspend fun addRecipe(recipe: Recipe) {
        recipeListRoomDao.addRecipe(recipe = recipe.toRecipeEntity())
    }

    override suspend fun addCategory(name: String) {
        recipeListRoomDao.addCategory(CategoryEntity(name = name))
    }

    override fun getCategories(): Flow<List<Category>> {
        return recipeListRoomDao.getCategories().map { categories ->
            categories.map { category ->
                category.toCategory()
            }
        }
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        recipeListRoomDao.deleteRecipe(recipe = recipe.toRecipeEntity())
    }

    override suspend fun editRecipe(recipe: Recipe) {
        recipeListRoomDao.updateRecipe(recipe = recipe.toRecipeEntity())
    }

    override suspend fun getRecipe(id: Int) {
        recipeListRoomDao.getRecipe(recipeId = id)
    }

    override fun getRecipeList(): Flow<List<Recipe>> {
        return recipeListRoomDao.getRecipeList().map { recipes ->
            recipes.map { recipe ->
                recipe.toRecipe()
            }
        }
    }
}