package com.example.wishingapp.WishingApp.Database

import android.content.Context
import androidx.room.Room

object Graph {
    lateinit var database: WishDatabase
    val wishRepository by lazy {
        WishRepository(wishDAO = database.wishDao())
    }

    fun provide(context:Context) {
        database = Room.databaseBuilder(context,WishDatabase::class.java,"wishlist1.db").build()
    }
}