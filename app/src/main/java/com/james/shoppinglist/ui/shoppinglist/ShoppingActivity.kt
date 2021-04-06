package com.james.shoppinglist.ui.shoppinglist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.james.shoppinglist.R
import com.james.shoppinglist.data.db.ShoppingDatabase
import com.james.shoppinglist.data.entities.ShoppingItem
import com.james.shoppinglist.data.other.ShoppingItemAdapter
import com.james.shoppinglist.data.repositories.ShoppingRepository
import kotlinx.android.synthetic.main.activity_shopping.*
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class ShoppingActivity : AppCompatActivity(),KodeinAware {
    override val kodein by kodein()
    private val factory:ShoppingViewModelFactory by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shopping)

        val viewModel = ViewModelProviders.of(this,factory).get(ShoppingViewModel::class.java)
        val adapter = ShoppingItemAdapter(listOf(),viewModel)
        rvShoppingItems.layoutManager = LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false)
        rvShoppingItems.adapter = adapter

        viewModel.getAllShoppingItems().observe(this, Observer {
            adapter.items = it
            adapter.notifyDataSetChanged()
            Log.d("TAG", "onCreate: ${adapter.items}")
        })

        fab.setOnClickListener {
            AddShoppingItemDialog(this,object:AddDialogListener{
                override fun onAddButtonClicked(item: ShoppingItem) {
                    viewModel.upsert(item)
                }
            }).show()
        }
    }
}
