package com.example.translatelesson1.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class Translation(
    @Expose @field:SerializedName("text") val translation: String?
) : Parcelable