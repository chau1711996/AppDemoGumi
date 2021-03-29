package com.example.appdemogumi

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.CheckBox
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.activity_test_layout.*
import kotlinx.android.synthetic.main.layout_title.view.*
import kotlinx.android.synthetic.main.one_item_yourname.view.*

class ItemAdapter(var l_Item: List<Item>) : RecyclerView.Adapter<ItemAdapter.ItemHolder>() {
    private lateinit var clicked: ListernOnClick

    inner class ItemHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var checkBox = CheckBox(itemView.context)
        init {
            var checkId = 100
            checkBox.id = checkId
            checkBox.buttonTintList = itemView.context.resources.getColorStateList(R.color.pink, itemView.context.theme)
            var cbParams = ConstraintLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT);
            cbParams.topToTop = itemView.id
            cbParams.rightToRight = itemView.id
            cbParams.bottomToBottom = itemView.id
            checkBox.layoutParams = cbParams
            checkBox.isClickable = false
            itemView.constrain1.addView(checkBox)
        }
        fun bind(item: Item) {
            itemView.tvName.apply {
                text = item.name
            }
            checkBox.apply {
                isChecked = item.checked
            }
            itemView.setOnClickListener {
                var check = !item.checked
                checkBox.apply {
                    isChecked = check
                }
                item.checked = check
                clicked.onClick(item, adapterPosition)
            }
        }
    }

    fun setOnClickItem(action: (Item, Int) -> Unit = {_,_ ->} ) {
        this.clicked = object : ListernOnClick {
            override fun onClick(item: Item, pos: Int) {
                action(item, pos)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {

        var v =
            LayoutInflater.from(parent.context).inflate(R.layout.one_item_yourname, parent, false)

        return ItemHolder(v)
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind(l_Item.get(position))
    }

    override fun getItemCount(): Int = l_Item.size
}