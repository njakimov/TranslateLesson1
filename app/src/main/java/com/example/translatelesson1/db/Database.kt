package com.example.lesson1_mvp.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.translatelesson1.db.MeaningsDao
import com.example.translatelesson1.db.SearchResultDao
import com.example.translatelesson1.db.SettingsDao
import com.example.translatelesson1.db.TranslationDao

@androidx.room.Database(
    entities = [RoomSearchResult::class, RoomMeanings::class, RoomTranslation::class, RoomSettings::class],
    version = 1
)
abstract class Database : RoomDatabase() {

    abstract val searchDao: SearchResultDao
    abstract val meaningDao: MeaningsDao
    abstract val translationDao: TranslationDao
    abstract val settingsDao: SettingsDao


    companion object {
        private const val DB_NAME = "databaseTranslate1.db"
        private var instance: Database? = null

        fun getInstance() = instance
            ?: throw RuntimeException("Database has not been created. Please call create(context)")

        fun create(context: Context?) {
            if (instance == null) {
                instance = Room
                    .databaseBuilder(context!!, Database::class.java, DB_NAME)
                    .fallbackToDestructiveMigrationOnDowngrade()
                    .build()
            }
        }
    }
}