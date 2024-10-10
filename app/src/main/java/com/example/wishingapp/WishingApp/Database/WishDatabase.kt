package com.example.wishingapp.WishingApp.Database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.wishingapp.WishingApp.Wish

@Database(
    entities = [Wish::class],
    version = 1,
    exportSchema = false
)
abstract class WishDatabase : RoomDatabase() {
    abstract fun wishDao():WishDAO
}