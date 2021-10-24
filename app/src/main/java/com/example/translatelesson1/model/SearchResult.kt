package com.example.translatelesson1.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
class SearchResult(
    @Expose @field:SerializedName("text") val text: String?,
    @Expose @field:SerializedName("meanings") var meanings: List<Meanings>?
) : Parcelable