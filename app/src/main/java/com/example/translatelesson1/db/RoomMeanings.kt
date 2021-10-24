package com.example.lesson1_mvp.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.translatelesson1.model.Meanings
import com.example.translatelesson1.model.Translation


@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomSearchResult::class,
        parentColumns = ["id"],
        childColumns = ["searchResultId"],
        onDelete = ForeignKey.CASCADE
    )]
)
class RoomMeanings(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var searchResultId: Long,
    var imageUrl: String,
)
