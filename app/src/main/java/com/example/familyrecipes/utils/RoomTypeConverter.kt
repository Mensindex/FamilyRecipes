package com.example.familyrecipes.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import com.example.familyrecipes.data.entities.CategoryEntity
import com.example.familyrecipes.data.entities.RecipeEntity
import com.example.familyrecipes.domain.models.Ingredient
import com.example.familyrecipes.domain.models.MethodStep
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.ByteArrayOutputStream
import java.time.LocalTime

class RoomTypeConverter {

    @TypeConverter
    fun fromListCategoryToString(value: List<CategoryEntity>): String =
        Gson().toJson(value, object : TypeToken<List<CategoryEntity>>() {}.type)

    @TypeConverter
    fun fromStringToListCategory(value: String): List<CategoryEntity> {
        return try {
            Gson().fromJson(value, object : TypeToken<List<CategoryEntity>>() {}.type)
        } catch (e: Exception) {
            emptyList()
        }
    }

    @TypeConverter
    fun fromRecipeToString(value: RecipeEntity): String =
        Gson().toJson(value, object : TypeToken<RecipeEntity>() {}.type)

    @TypeConverter
    fun fromStringToRecipe(value: String): RecipeEntity? {
        return try {
            Gson().fromJson(value, object : TypeToken<RecipeEntity>() {}.type)
        } catch (e: Exception) {
            null
        }
    }

    @TypeConverter
    fun fromListIngredientToString(value: List<Ingredient>): String {
        return Gson().toJson(value, object : TypeToken<List<Ingredient>>() {}.type)
    }

    @TypeConverter
    fun fromStringToListIngredient(value: String): List<Ingredient> {
        return try {
            Gson().fromJson(value, object : TypeToken<List<Ingredient>>() {}.type)
        } catch (e: Exception) {
            emptyList()
        }
    }

    @TypeConverter
    fun fromListMethodStepToString(value: List<MethodStep>): String =
        Gson().toJson(value, object : TypeToken<List<MethodStep>>() {}.type)

    @TypeConverter
    fun fromStringToListMethodStep(value: String): List<MethodStep> {
        return try {
            Gson().fromJson(value, object : TypeToken<List<MethodStep>>() {}.type)
        } catch (e: Exception) {
            emptyList()
        }
    }

    @TypeConverter
    fun fromBitmap(bitmap: Bitmap?): ByteArray {
        val outputStream = ByteArrayOutputStream()
        bitmap?.compress(Bitmap.CompressFormat.JPEG, 100, outputStream)
        return outputStream.toByteArray()
    }

    @TypeConverter
    fun toBitmap(byteArray: ByteArray): Bitmap? {
        return BitmapFactory.decodeByteArray(byteArray, 0, byteArray.size)
    }

    @TypeConverter
    fun fromLocalTime(time: LocalTime) = time.toNanoOfDay()

    @TypeConverter
    fun toLocalTime(time: Long): LocalTime? = LocalTime.ofNanoOfDay(time)
}