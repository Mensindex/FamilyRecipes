package com.example.familyrecipes

import android.app.Application
import com.example.familyrecipes.data.database.room.AppRoomDatabase
import com.example.familyrecipes.data.database.room.RecipeListRoomDao

class App : Application() {

    companion object {
        lateinit var dao: RecipeListRoomDao
    }

    override fun onCreate() {
        super.onCreate()
        dao = AppRoomDatabase.getInstance(application = this).getRecipeListRoomDao()
    }

}