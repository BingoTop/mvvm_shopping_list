package com.james.shoppinglist.ui.shoppinglist

import androidx.lifecycle.ViewModel
import com.james.shoppinglist.data.entities.ShoppingItem
import com.james.shoppinglist.data.repositories.ShoppingRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingViewModel(private val repository: ShoppingRepository) :ViewModel(){
    // 데이터베이스 작업은 보통 IO, Room 은 mainsafety를 제공하기 때문에 Main Default는 오래 걸리고 복잡한 작업
    fun upsert(item:ShoppingItem) = CoroutineScope(Dispatchers.Main).launch {
        repository.upsert(item)
    }

    fun delete(item: ShoppingItem) =  CoroutineScope(Dispatchers.Main).launch {
        repository.delete(item)
    }

    fun getAllShoppingItems()= repository.getAllShoppingItems()
}