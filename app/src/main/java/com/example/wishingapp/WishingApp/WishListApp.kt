package com.example.wishingapp.WishingApp

import android.app.Application
import com.example.wishingapp.WishingApp.Database.Graph

class WishListApp:Application() {
    override fun onCreate() {
        super.onCreate()
        Graph.provide(this)
    }
}