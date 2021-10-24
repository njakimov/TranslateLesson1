package com.example.translatelesson1.db

import androidx.room.*
import com.example.lesson1_mvp.db.RoomMeanings


@Dao
interface MeaningsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(meaning: RoomMeanings) : Long

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg meanings: RoomMeanings)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(meanings: List<RoomMeanings>)

    @Update
    fun update(meaning: RoomMeanings)

    @Update
    fun update(vararg meanings: RoomMeanings)

    @Update
    fun update(meanings: List<RoomMeanings>)

    @Delete
    fun delete(meaning: RoomMeanings)

    @Delete
    fun delete(vararg meanings: RoomMeanings)

    @Delete
    fun delete(meanings: List<RoomMeanings>)

    @Query("SELECT * FROM RoomMeanings")
    fun getAll(): List<RoomMeanings>

    @Query("SELECT * FROM RoomMeanings WHERE searchResultId = :id")
    fun findForWord(id: Int): List<RoomMeanings>?
}