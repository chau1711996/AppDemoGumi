package com.example.appdemogumi

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_test_layout.*
import kotlinx.android.synthetic.main.layout_title.view.*

class TestLayoutActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_layout)
        var listTitle = mutableListOf<Item>()

        var listItem = mutableListOf<Item>()
        listItem.run {
            add(Item("Chau Dang", false))
            add(Item("Hai Anh", false))
            add(Item("Tuan Tu", false))
        }



        incTitle.btnTitle.setOnClickListener {
            listTitle.clear()
            listItem.forEach {
                if(it.checked)
                    listTitle.add(it)
            }
            Toast.makeText(applicationContext, listTitle.toString(), Toast.LENGTH_SHORT).show()
        }

        var itemAdapter = ItemAdapter(listItem)
        itemAdapter.setOnClickItem{item, position ->
            incTitle.btnTitle.text = "${item.name} pos: ${position} ${item.checked}"
        }
        var linerLayout = LinearLayoutManager(applicationContext)
        rec_YourName.apply {
            adapter = itemAdapter
            layoutManager = linerLayout
            setHasFixedSize(true)
        }

        fabAddItem.setOnClickListener {
            val build = AlertDialog.Builder(this)
            val view = layoutInflater.inflate(R.layout.dialog_add_tasklog, null)
            build.setTitle("Thêm Item")
            build.setView(view)
            build.setCancelable(false)
            val alert = build.create()
            alert.show()
            val btnAddOke = view.findViewById(R.id.btnAddOke) as Button
            val btnAddCancel = view.findViewById(R.id.btnAddCancel) as Button
            val name = view.findViewById(R.id.etName) as EditText
            btnAddCancel.setOnClickListener{
                alert.dismiss()
            }

            btnAddOke.setOnClickListener{
                val item = Item(name.text.toString(), false)
                listItem.add(item)
                itemAdapter.notifyDataSetChanged()
                alert.dismiss()
            }
        }

    }
}