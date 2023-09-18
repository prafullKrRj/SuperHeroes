package com.example.superheroes.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class Hero(
    @StringRes val name: Int,
    @StringRes val about: Int,
    @DrawableRes val image: Int
)
