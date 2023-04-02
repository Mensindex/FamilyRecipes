package com.example.familyrecipes.data.database.room

import com.example.familyrecipes.data.entities.*
import com.example.familyrecipes.domain.RecipeListRepository
import com.example.familyrecipes.domain.models.Category
import com.example.familyrecipes.domain.models.Recipe
import com.example.familyrecipes.domain.models.RecipeWithCategories
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RecipeListRepositoryImpl(private val recipeListRoomDao: RecipeListRoomDao) :
    RecipeListRepository {

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

    override suspend fun deleteCategory(categoryId: Long) {
        recipeListRoomDao.deleteCategory(categoryId = categoryId)
    }

    override suspend fun deleteRecipe(recipe: Recipe) {
        recipeListRoomDao.deleteRecipe(recipe = recipe.toRecipeEntity())
    }

    override suspend fun editRecipe(recipe: Recipe) {
        recipeListRoomDao.updateRecipe(recipe = recipe.toRecipeEntity())
    }

    override fun getRecipe(id: Long): Flow<Recipe> {
        return recipeListRoomDao.getRecipe(recipeId = id).map { recipeEntity ->
            recipeEntity.toRecipe()
        }
    }

    override fun getRecipeList(): Flow<List<Recipe>> {
        return recipeListRoomDao.getRecipeList().map { recipes ->
            recipes.map { recipe ->
                recipe.toRecipe()
            }
        }
    }

    override suspend fun addRecipeWithCategories(recipe: RecipeWithCategories) {
        recipeListRoomDao.insertRecipeWithCategories(recipeWithCategoriesEntity = recipe.toRecipeWithCategoriesEntity())
    }

    override fun getRecipeListByCategory(id: Long): Flow<List<Recipe>> {
        return recipeListRoomDao.getRecipesByCategoryId(categoryId = id).map { listRecipeEntity ->
            listRecipeEntity.map { recipeEntity ->
                recipeEntity.toRecipe()
            }
        }
    }

    //    override fun getRecipesCountByCategoryId(categoryId: Long): Flow<Long> {
//        return recipeListRoomDao.getRecipesCountByCategoryId(categoryId = categoryId)
//    }
    override fun getRecipeCountsByCategoryIds(categoryIds: List<Long>): Flow<List<Int>> {
        return recipeListRoomDao.getRecipeCountsByCategoryIds(categoryIds = categoryIds)
    }

    override fun getCategoryById(categoryId: Long): Flow<Category> {
        return recipeListRoomDao.getCategory(categoryId = categoryId).map { it.toCategory() }
    }
}