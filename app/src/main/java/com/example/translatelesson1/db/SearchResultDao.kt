package com.example.translatelesson1.db

import androidx.room.*
import com.example.lesson1_mvp.db.RoomSearchResult


@Dao
interface SearchResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(word: RoomSearchResult): Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg words: RoomSearchResult)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(words: List<RoomSearchResult>)

    @Update
    fun update(word: RoomSearchResult)

    @Update
    fun update(vararg words: RoomSearchResult)

    @Update
    fun update(words: List<RoomSearchResult>)

    @Delete
    fun delete(word: RoomSearchResult)

    @Delete
    fun delete(vararg words: RoomSearchResult)

    @Delete
    fun delete(words: List<RoomSearchResult>)

    @Query("SELECT * FROM RoomSearchResult")
    fun getAll(): List<RoomSearchResult>

    @Query("SELECT * FROM RoomSearchResult WHERE text = :text")
    fun findByWord(text: String): List<RoomSearchResult>?

    @Query("SELECT * FROM RoomSearchResult WHERE text like '%' || :text || '%'")
    fun findAllByWord(text: String): List<RoomSearchResult>?
}