package com.example.lesson1_mvp.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey


@Entity(indices = arrayOf(Index("text", unique = true)))
class RoomSearchResult(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    @ColumnInfo(name = "text") var text: String,
)
