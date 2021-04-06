package com.james.shoppinglist.data.repositories

import com.james.shoppinglist.data.db.ShoppingDatabase
import com.james.shoppinglist.data.entities.ShoppingItem

class ShoppingRepository (private val db:ShoppingDatabase){
    suspend fun upsert(item:ShoppingItem) =  db.getShoppingDao().upsert(item)
    suspend fun delete(item:ShoppingItem) = db.getShoppingDao().delete(item)
    fun getAllShoppingItems() = db.getShoppingDao().getAllShoppingItem()
}