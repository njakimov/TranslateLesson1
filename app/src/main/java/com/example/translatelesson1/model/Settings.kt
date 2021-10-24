package com.example.translatelesson1.model

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Settings(
    @Expose val id: String? = null,
    @Expose val setting_name: String? = null,
    @Expose val value: String? = null,
) : Parcelable
