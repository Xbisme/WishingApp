package com.example.wishingapp.WishingApp.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.wishingapp.WishingApp.Wish
import kotlinx.coroutines.flow.Flow

@Dao
abstract class WishDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract fun addAWish(wishEntity: Wish)

    @Query("Select * from 'wish-table'")
    abstract fun getAllWishes(): Flow<List<Wish>>

    @Query("Select * from 'wish-table' where id=:id")
    abstract fun getWishById(id:Long): Flow<Wish>

    @Update
    abstract fun updateWish(wishEntity: Wish)

    @Delete
    abstract fun deleteWish(wishEntity: Wish)


}