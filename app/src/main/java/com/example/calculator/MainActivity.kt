package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView


class MainActivity : AppCompatActivity(), View.OnClickListener {

    lateinit var textResult: TextView

    var state: Int = 1
    var op: Int = 0
    var op1: Int = 0
    var op2: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        textResult = findViewById(R.id.text_result)

        // Nút số
        findViewById<Button>(R.id.btn0).setOnClickListener(this)
        findViewById<Button>(R.id.btn1).setOnClickListener(this)
        findViewById<Button>(R.id.btn2).setOnClickListener(this)
        findViewById<Button>(R.id.btn3).setOnClickListener(this)
        findViewById<Button>(R.id.btn4).setOnClickListener(this)
        findViewById<Button>(R.id.btn5).setOnClickListener(this)
        findViewById<Button>(R.id.btn6).setOnClickListener(this)
        findViewById<Button>(R.id.btn7).setOnClickListener(this)
        findViewById<Button>(R.id.btn8).setOnClickListener(this)
        findViewById<Button>(R.id.btn9).setOnClickListener(this)

        // Nút phép toán
        findViewById<Button>(R.id.btnAdd).setOnClickListener(this)
        findViewById<Button>(R.id.btnSub).setOnClickListener(this)
        findViewById<Button>(R.id.btnMul).setOnClickListener(this)
        findViewById<Button>(R.id.btnDiv).setOnClickListener(this)
        findViewById<Button>(R.id.btnEqual).setOnClickListener(this)

        // Các nút chức năng khác
        findViewById<Button>(R.id.btnC).setOnClickListener(this)
        findViewById<Button>(R.id.btnBS).setOnClickListener(this)
        findViewById<Button>(R.id.btnCE).setOnClickListener(this)

    }

    override fun onClick(p0: View?) {
        val id = p0?.id
        when (id) {
            R.id.btn0 -> addDigit(0)
            R.id.btn1 -> addDigit(1)
            R.id.btn2 -> addDigit(2)
            R.id.btn3 -> addDigit(3)
            R.id.btn4 -> addDigit(4)
            R.id.btn5 -> addDigit(5)
            R.id.btn6 -> addDigit(6)
            R.id.btn7 -> addDigit(7)
            R.id.btn8 -> addDigit(8)
            R.id.btn9 -> addDigit(9)
            R.id.btnAdd -> {
                op = 1
                state = 2
            }
            R.id.btnSub -> {
                op = 2
                state = 2
            }
            R.id.btnMul -> {
                op = 3
                state = 2
            }
            R.id.btnDiv -> {
                op = 4
                state = 2
            }
            R.id.btnEqual -> calculateResult()
            R.id.btnC -> clearAll()
            R.id.btnBS -> backspace()
            R.id.btnCE -> clearEntry()
        }
    }

    fun addDigit(c: Int) {
        if (state == 1) {
            op1 = op1 * 10 + c
            textResult.text = "$op1"
        } else {
            op2 = op2 * 10 + c
            textResult.text = "$op2"
        }
    }

    fun calculateResult() {
        var result = 0
        when (op) {
            1 -> result = op1 + op2
            2 -> result = op1 - op2
            3 -> result = op1 * op2
            4 -> if (op2 != 0) result = op1 / op2
        }
        textResult.text = "$result"
        state = 1
        op1 = result
        op2 = 0
        op = 0
    }

    fun clearEntry() {
        if (state == 1) {
            op1 = 0
            textResult.text = "0"
        } else {
            op2 = 0
            textResult.text = if (op1 != 0) "$op1" else "0"
        }
    }

    fun clearAll() {
        op1 = 0
        op2 = 0
        op = 0
        state = 1
        textResult.text = "0"
    }

    fun backspace() {
        if (state == 1) {
            op1 /= 10
            textResult.text = if (op1 == 0) "0" else "$op1"
        } else {
            op2 /= 10
            textResult.text = if (op2 == 0) "0" else "$op2"
        }
    }
}
