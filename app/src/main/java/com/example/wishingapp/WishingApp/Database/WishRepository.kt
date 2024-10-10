package com.example.wishingapp.WishingApp.Database

import com.example.wishingapp.WishingApp.Wish
import kotlinx.coroutines.flow.Flow

class WishRepository(private val wishDAO: WishDAO) {
    suspend fun addWish(wish: Wish){
        wishDAO.addAWish(wish)
    }

    fun getAllWishes(): Flow<List<Wish>> = wishDAO.getAllWishes()

    fun getWishById(id:Long): Flow<Wish> {
        return wishDAO.getWishById(id)
    }

    suspend fun updateWish(wishEntity: Wish) {
        wishDAO.updateWish(wishEntity)
    }

    suspend fun deleteWish(wishEntity: Wish) {
        wishDAO.deleteWish(wishEntity)
    }
}