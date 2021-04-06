package com.james.shoppinglist.ui.shoppinglist

import com.james.shoppinglist.data.entities.ShoppingItem

interface AddDialogListener {
    fun onAddButtonClicked(item: ShoppingItem)

}