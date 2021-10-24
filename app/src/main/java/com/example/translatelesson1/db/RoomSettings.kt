package com.example.lesson1_mvp.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
class RoomSettings(
    @PrimaryKey var id: String,
    var setting_name: String,
    var value: String
)
