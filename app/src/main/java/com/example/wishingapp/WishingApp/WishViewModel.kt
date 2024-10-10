package com.example.wishingapp.WishingApp

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.wishingapp.WishingApp.Database.Graph
import com.example.wishingapp.WishingApp.Database.WishRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch


class WishViewModel(private val wishRepository: WishRepository = Graph.wishRepository)
    : ViewModel()
{
    var _wish by mutableStateOf("")
    var _wishDes by mutableStateOf("")

    fun onWishChanged(newWish:String) {
        _wish = newWish
    }
    fun onDescriptionChanged(newDes:String) {
        _wishDes = newDes
    }

    lateinit var getAllWishes: Flow<List<Wish>>

    init {
        viewModelScope.launch {
            getAllWishes = wishRepository.getAllWishes()
        }

    }
    fun addWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.addWish(wish)
        }
    }
    fun updateWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.updateWish(wish)
        }
    }
    fun deleteWish(wish: Wish) {
        viewModelScope.launch(Dispatchers.IO) {
            wishRepository.deleteWish(wish)
        }
    }
    fun getWishById(id: Long): Flow<Wish> {
        return wishRepository.getWishById(id)
    }

}
