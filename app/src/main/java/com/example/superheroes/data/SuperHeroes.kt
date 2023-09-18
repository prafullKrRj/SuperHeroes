package com.example.superheroes.data

import com.example.superheroes.R
import com.example.superheroes.model.Hero

object SuperHeroes {
    val heroes = listOf(
        Hero(
            name = R.string.hero1,
            about = R.string.description1,
            image = R.drawable.android_superhero1
        ),
        Hero(
            R.string.hero2,
            R.string.description2,
            R.drawable.android_superhero2
        ),
        Hero(
            R.string.hero3,
            R.string.description3,
            R.drawable.android_superhero3
        ),
        Hero(
            R.string.hero4,
            R.string.description4,
            R.drawable.android_superhero4
        ),
        Hero(
            R.string.hero5,
            R.string.description5,
            R.drawable.android_superhero5
        ),
        Hero(
            R.string.hero6,
            R.string.description6,
            R.drawable.android_superhero6
        )
    )
}