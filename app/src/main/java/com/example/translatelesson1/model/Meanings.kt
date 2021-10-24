package com.example.translatelesson1.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Meanings(
    @Expose @field:SerializedName("translation") val translation: Translation?,
    @Expose @field:SerializedName("imageUrl") val imageUrl: String?
) : Parcelable