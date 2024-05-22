package com.example.venomshop.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Clothes(
    @StringRes val brand: Int,
    @StringRes val title: Int,
    @StringRes val description: Int,
    val price: String,
    val grade: Double,
    @DrawableRes val image: Int
)
