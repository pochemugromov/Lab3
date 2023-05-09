package com.example.lab3

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var check = false
    var mathAct = ""
    var arg1 = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickNum(view: View) {
        val arg = findViewById<TextView>(R.id.out)
        if (arg.text.toString() == "0") {
            arg.text = findViewById<Button>(view.id).text.toString()
            check = false
        }
        else {
            if (check) {
                arg.text = findViewById<Button>(view.id).text.toString()
                check = false
            } else arg.append(findViewById<Button>(view.id).text.toString())
        }
    }

    fun onClickZero(view: View) {
        val arg = findViewById<TextView>(R.id.out)
        if (check) {
            arg.text = findViewById<Button>(view.id).text.toString()
            check = false
        }
        else if (arg.text.toString() != "0" || arg.text.toString() == "0.0") arg.append(findViewById<Button>(view.id).text.toString())
    }

    fun onClickPoint(view: View) {
        val arg = findViewById<TextView>(R.id.out)
        if (!arg.text.toString().contains('.')) {
            arg.append(".")
        }
    }

    fun calculate(arg2: String): String{
        if (mathAct == "+") {
            return (arg1.toDouble() + (arg2.toDouble())).toString()
        }
        else if (mathAct == "-") {
            return (arg1.toDouble() - (arg2.toDouble())).toString()
        }
        else if (mathAct == "*"
        ) {
            return (this.arg1.toDouble() * (arg2.toDouble())).toString()
        }
        else {
            if (mathAct == "/"
            ) return when {
                when {
                    arg2.toDouble() != 0.0 -> {
                        true
                    }
                    else -> {
                        false
                    }
                } -> {
                    (arg1.toDouble() / (arg2.toDouble())).toString()
                }
                else -> {
                    check = true
                    "Ошибка!"
                }
            }
            else {
                check = true
                return "Error"
            }
        }
    }

    fun value(view: View) {
        val arg = findViewById<TextView>(R.id.out)
        if (arg.text.isNotEmpty()) arg.text = if (arg.text.first() == '-') arg.text.toString().substring(1, arg.text.length)
                else {
                    "-" + arg.text
                } else arg.text = "-"
    }

    fun onClickAction(view: View){
        val expression = findViewById<TextView>(R.id.formula)
        val arg = findViewById<TextView>(R.id.out)
        when {
            arg.text.toString() == "Ошибка!" || arg.text.toString() == "" -> {
                clear(view)
            }
            arg1 == "" || !check && arg1 == arg.text.toString() -> {
                mathAct = findViewById<Button>(view.id).text.toString()
                arg1 = arg.text.toString()
                expression.text = arg1 + mathAct
                check = true
            }
            else -> {
                arg1 = calculate(arg.text.toString())
                mathAct = findViewById<Button>(view.id).text.toString()
                expression.append(arg.text.toString() + mathAct)
                arg.text = arg1
                check = true
            }
        }
    }

    fun onClickEquals(view: View) {
        when {
            mathAct != "" -> {
                val expression = findViewById<TextView>(R.id.formula)
                val arg = findViewById<TextView>(R.id.out)
                var result = calculate(arg.text.toString())
                arg.text = if (result.endsWith(".0")) result.dropLast(2) else result
                expression.text = ""
                check = true
                arg1 = ""
                mathAct = ""
            }
        }
    }

    fun clear(view: View) {
        var check = false
        mathAct = ""
        arg1 = ""
        findViewById<TextView>(R.id.out).text = ""
        findViewById<TextView>(R.id.formula).text = ""
    }
}