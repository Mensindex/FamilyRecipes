package com.example.familyrecipes.data.entities

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.familyrecipes.domain.models.RecipeWithCategories
import com.example.familyrecipes.utils.CATEGORY_ID
import com.example.familyrecipes.utils.ID
import com.example.familyrecipes.utils.RECIPE_ID

data class RecipeWithCategoriesEntity(
    @Embedded
    val recipe: RecipeEntity,
    @Relation(
        parentColumn = ID,
        entityColumn = ID,
        associateBy = Junction(
            value = RecipeCategoryCrossRef::class,
            parentColumn = RECIPE_ID,
            entityColumn = CATEGORY_ID,
        )
    )
    val categories: List<CategoryEntity>,
)

fun RecipeWithCategoriesEntity.toRecipeWithCategories(): RecipeWithCategories{
    return RecipeWithCategories(
        recipe = recipe.toRecipe(),
        categories = categories.map { it.toCategory() }
    )
}

fun RecipeWithCategories.toRecipeWithCategoriesEntity(): RecipeWithCategoriesEntity{
    return RecipeWithCategoriesEntity(
        recipe = recipe.toRecipeEntity(),
        categories = categories.map { it.toCategoryEntity() }
    )
}
