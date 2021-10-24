package com.example.translatelesson1.db

import androidx.room.*
import com.example.lesson1_mvp.db.RoomSettings

@Dao
interface SettingsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(setting: RoomSettings)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg settings: RoomSettings)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(settings: List<RoomSettings>)

    @Update
    fun update(setting: RoomSettings)

    @Update
    fun update(vararg settings: RoomSettings)

    @Update
    fun update(settings: List<RoomSettings>)

    @Delete
    fun delete(setting: RoomSettings)

    @Delete
    fun delete(vararg settings: RoomSettings)

    @Delete
    fun delete(settings: List<RoomSettings>)

    @Query("SELECT * FROM RoomSettings")
    fun getAll(): List<RoomSettings>

    @Query("SELECT * FROM RoomSettings WHERE setting_name = :setting_name LIMIT 1")
    fun findBySetting(setting_name: String): RoomSettings?
}