package com.example.familyrecipes.data.database.room

import android.app.Application
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.familyrecipes.data.entities.CategoryEntity
import com.example.familyrecipes.data.entities.RecipeCategoryCrossRef
import com.example.familyrecipes.data.entities.RecipeEntity
import com.example.familyrecipes.utils.RoomTypeConverter

@Database(entities = [RecipeEntity::class, CategoryEntity::class, RecipeCategoryCrossRef::class], version = 2, exportSchema = false)
@TypeConverters(RoomTypeConverter::class)
abstract class AppRoomDatabase : RoomDatabase() {

    abstract fun getRecipeListRoomDao(): RecipeListRoomDao

    companion object {
        @Volatile
        private var INSTANCE: AppRoomDatabase? = null
        private val LOCK = Any()
        private const val DB_NAME = "recipes.db"

        fun getInstance(application: Application): AppRoomDatabase {
            INSTANCE?.let {
                return it
            }
            synchronized(LOCK) {
                INSTANCE?.let {
                    return it
                }
                val db = Room.databaseBuilder(
                    context = application,
                    klass = AppRoomDatabase::class.java,
                    name = DB_NAME,
                )
//                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = db
                return db
            }
        }
    }


}