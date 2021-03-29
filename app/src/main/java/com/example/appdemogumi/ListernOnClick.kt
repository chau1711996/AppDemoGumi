package com.example.appdemogumi

import android.content.Context
import android.view.View

interface ListernOnClick {
    fun onClick(item: Item, pos: Int)
}