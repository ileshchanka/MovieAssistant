package com.example.movieassistant.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Movie(
    val imageId: Int,
    val title: String,
    val overview: String
) : Parcelable
