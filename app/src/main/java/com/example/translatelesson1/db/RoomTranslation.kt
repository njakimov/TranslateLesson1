package com.example.lesson1_mvp.db

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import com.example.translatelesson1.model.Meanings


@Entity(
    foreignKeys = [ForeignKey(
        entity = RoomMeanings::class,
        parentColumns = ["id"],
        childColumns = ["meaningId"],
        onDelete = ForeignKey.CASCADE
    )]
)
class RoomTranslation(
    @PrimaryKey(autoGenerate = true) var id: Long = 0,
    var meaningId: Long,
    var text: String,
)
