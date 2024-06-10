package com.example.maketeam_app.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Position(
    val positionName : String,
    val positionDetail : String,
    val positionPeople : String
) : Parcelable
