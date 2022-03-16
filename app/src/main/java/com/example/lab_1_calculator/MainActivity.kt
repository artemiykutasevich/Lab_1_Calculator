package com.example.lab_1_calculator

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Numbers
        btn_one.setOnClickListener { pokaz("1", true)}
        btn_two.setOnClickListener { pokaz("2", true)}
        btn_three.setOnClickListener { pokaz("3", true)}
        btn_four.setOnClickListener { pokaz("4", true)}
        btn_five.setOnClickListener { pokaz("5", true)}
        btn_six.setOnClickListener { pokaz("6", true)}
        btn_seven.setOnClickListener { pokaz("7", true)}
        btn_eight.setOnClickListener { pokaz("8", true)}
        btn_nine.setOnClickListener { pokaz("9", true)}
        btn_zero.setOnClickListener { pokaz("0", true)}
        btn_dot.setOnClickListener { pokaz(".", true)}

        // Operators
        btn_plus.setOnClickListener { pokaz("+", false)}
        btn_sub.setOnClickListener { pokaz("-", false)}
        btn_div.setOnClickListener { pokaz("/", false)}
        btn_multi.setOnClickListener { pokaz("*", false)}
        btn_open.setOnClickListener { pokaz("(", false)}
        btn_close.setOnClickListener { pokaz(")", false)}

        btn_back.setOnClickListener {
            val stroka = btn_Expression.text.toString()
            if (stroka.isNotEmpty()) {
                btn_Expression.text = stroka.substring(0, stroka.length - 1)
            }
            btn_Result.text = ""
        }

        btn_AC.setOnClickListener {
            btn_Result.text = ""
            btn_Expression.text = ""
        }

        btn_equals.setOnClickListener {
            try {
                var expression = ExpressionBuilder(btn_Expression.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    btn_Result.text = longResult.toString()
                } else {
                    btn_Result.text = result.toString()
                }
            } catch (e: Exception) {
                Log.d("error", "code: " + e.message)
            }
        }
    }

    fun pokaz(stroka: String, ochistka: Boolean) {
        if(btn_Expression.text.isEmpty()) {
            btn_Result.text = ""
        }
        if (ochistka) {
            btn_Result.text = ""
            btn_Expression.append(stroka)
        }
        else {
            btn_Expression.append(btn_Result.text)
            btn_Expression.append(stroka)
            btn_Result.text = ""
        }
    }
}