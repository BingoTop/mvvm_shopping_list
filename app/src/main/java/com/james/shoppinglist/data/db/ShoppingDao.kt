package com.james.shoppinglist.data.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.james.shoppinglist.data.entities.ShoppingItem

@Dao
interface ShoppingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(item: ShoppingItem)

    @Delete
    suspend fun delete(item: ShoppingItem)

    @Query("SELECT * FROM shopping_item")
    fun getAllShoppingItem():LiveData<List<ShoppingItem>>
}