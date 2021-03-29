package com.example.appdemogumi

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var click: ListenerOnclick? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tv = TextView(this)
        tv.apply {
            text = "hello textview"
        }

        tv2.setOnClickListener {
            click?.onClickListener(it)
        }
        clickGetView {
            Log.i("myTouch", "myTouch")
        }

        btnTestClick.setOnClickListener {

        }

        btnTestClick.setOnTouchListener { view, motionEvent ->
            val action = motionEvent.actionMasked
            handleTestTouch(motionEvent)
            when (action) {
                MotionEvent.ACTION_UP -> {
                    num_X_UP = motionEvent.rawX
                    tvUP.apply {
                        text = "X_UP = $num_X_UP"
                    }
                    tv2.apply {
                        text = "Sum = ${Math.abs(num_X_UP - num_X_DOWN)}"
                    }
                    val delta = motionEvent.rawX - num_X_DOWN
                    if (Math.abs(delta) < 10F)
                        click?.onClickListener(view)
                }
                MotionEvent.ACTION_DOWN -> {
                    num_X_DOWN = motionEvent.rawX
                    tvDown.apply {
                        text = "X_DOWN = $num_X_DOWN"
                    }
                }
            }

            view.performClick()
            true
        }
    }

    var num_X_DOWN = 0F
    var num_X_UP = 0F

    fun clickGetView(action: (View) -> Unit) {
        click = object : ListenerOnclick {
            override fun onClickListener(view: View) {
                action(view)
            }
        }
    }

    private fun handleTestTouch(m: MotionEvent) {
        val action = m.actionMasked
        val x = m.getX()
        val y = m.getY()
        var actionString: String
        when (action) {
            MotionEvent.ACTION_DOWN -> {
                actionString = "DOWN"
                txt.apply {
                    text = "Action: $actionString X: $x Y: $y"
                }
            }
            MotionEvent.ACTION_UP -> {
                actionString = "UP"
                txt.apply {
                    text = "Action: $actionString X: $x Y: $y"
                }
            }
            else -> actionString = ""
        }
        actionString = "UP"
        tv2.apply {
            text = "Action: $actionString X: $x Y: $y"
        }
    }

    private fun handleTouch(m: MotionEvent) {
        val pointerCount = m.pointerCount

        for (i in 0 until pointerCount) {
            val x = m.getX(i)
            val y = m.getY(i)
            val id = m.getPointerId(i)
            val action = m.actionMasked
            val actionIndex = m.actionIndex
            var actionString: String

            when (action) {
                MotionEvent.ACTION_DOWN -> actionString = "DOWN"
                MotionEvent.ACTION_UP -> actionString = "UP"
                MotionEvent.ACTION_POINTER_DOWN -> actionString = "PNTR DOWN"
                MotionEvent.ACTION_POINTER_UP -> actionString = "PNTR UP"
                MotionEvent.ACTION_MOVE -> actionString = "MOVE"
                else -> actionString = ""
            }

            val touchStatus =
                "Action: $actionString Index: $actionIndex ID: $id X: $x Y: $y"

            if (id == 0)
                txt.text = touchStatus
            else
                tv2.text = touchStatus
        }
    }


}

interface ListenerOnclick {
    fun onClickListener(view: View) {

    }

    fun onTouchChanged(view: View) {

    }
}