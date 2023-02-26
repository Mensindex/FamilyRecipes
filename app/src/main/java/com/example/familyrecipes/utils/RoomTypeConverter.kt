package com.example.familyrecipes.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.room.TypeConverter
import com.example.familyrecipes.data.entities.CategoryEntity
import com.example.familyrecipes.domain.models.Ingredient
import com.example.familyrecipes.domain.models.MethodStep
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.io.ByteArrayOutputStream
import java.time.LocalTime

inline fun <reified T> Gson.fromJson(json: String): T =
    fromJson(json, object : TypeToken<T>() {}.type)

class RoomTypeConverter {

    @TypeConverter
    fun fromListCategoryToString(value: List<CategoryEntity>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromStringToListCategory(value: String): List<CategoryEntity> {
        return try {
            Gson().fromJson(value)
        } catch (e: Exception) {
            emptyList()
        }
    }

    @TypeConverter
    fun fromListIngredientToString(value: List<Ingredient>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromStringToListIngredient(value: String): List<Ingredient> {
        return try {
            Gson().fromJson(value)
        } catch (e: Exception) {
            emptyList()
        }
    }

    @TypeConverter
    fun fromListMethodStepToString(value: List<MethodStep>): String {
        return Gson().toJson(value)
    }

    @TypeConverter
    fun fromStringToListMethodStep(value: String): List<MethodStep> {
        return try {
            Gson().fromJson(value)
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