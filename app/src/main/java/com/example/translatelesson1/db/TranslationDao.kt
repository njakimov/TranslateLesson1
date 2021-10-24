package com.example.translatelesson1.db

import androidx.room.*
import com.example.lesson1_mvp.db.RoomTranslation
import com.example.translatelesson1.model.Meanings
import com.example.translatelesson1.model.SearchResult

@Dao
interface TranslationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(translation: RoomTranslation): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg translations: RoomTranslation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(translations: List<RoomTranslation>)

    @Update
    fun update(translation: RoomTranslation)

    @Update
    fun update(vararg translations: RoomTranslation)

    @Update
    fun update(translations: List<RoomTranslation>)

    @Delete
    fun delete(translation: RoomTranslation)

    @Delete
    fun delete(vararg translations: RoomTranslation)

    @Delete
    fun delete(translations: List<RoomTranslation>)

    @Query("SELECT * FROM RoomTranslation")
    fun getAll(): List<RoomTranslation>

    @Query("SELECT * FROM RoomTranslation WHERE meaningId = :id LIMIT 1")
    fun findForMeaning(id: Int): RoomTranslation?
}