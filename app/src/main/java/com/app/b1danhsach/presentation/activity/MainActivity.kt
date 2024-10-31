package com.app.b1danhsach.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.app.b1danhsach.R
import android.widget.*


class MainActivity : AppCompatActivity() {

    private lateinit var etNumber: EditText
    private lateinit var rbEven: RadioButton
    private lateinit var rbOdd: RadioButton
    private lateinit var rbSquare: RadioButton
    private lateinit var btnShow: Button
    private lateinit var listView: ListView
    private lateinit var radioGroup: RadioGroup


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Ánh xạ view
        etNumber = findViewById(R.id.etNumber)
        rbEven = findViewById(R.id.rbEven)
        rbOdd = findViewById(R.id.rbOdd)
        radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        rbSquare = findViewById(R.id.rbSquare)
        btnShow = findViewById(R.id.btnShow)
        listView = findViewById(R.id.listView)

        // Xử lý sự kiện nhấn nút Show
        btnShow.setOnClickListener {
            val input = etNumber.text.toString()

            // Kiểm tra nếu nhập không hợp lệ
            if (input.isEmpty() || input.toIntOrNull() == null || input.toInt() <= 0) {
                Toast.makeText(this, "Vui lòng nhập số dương", Toast.LENGTH_SHORT).show()
                listView.adapter = null // Xóa danh sách trước đó
                return@setOnClickListener
            }

            if (radioGroup.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Vui lòng chọn loại số.", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val n = input.toInt()
            val numbers = when {
                rbEven.isChecked -> getEvenNumbers(n)
                rbOdd.isChecked -> getOddNumbers(n)
                rbSquare.isChecked -> getSquareNumbers(n)
                else -> emptyList()
            }

            // Hiển thị danh sách trong ListView
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, numbers)
            listView.adapter = adapter
        }
    }

    // Hàm lấy các số chẵn từ 0 đến n
    private fun getEvenNumbers(n: Int): List<Int> {
        val evenNumbers = mutableListOf<Int>()
        for (i in 0..n step 2) {
            evenNumbers.add(i)
        }
        return evenNumbers
    }

    // Hàm lấy các số lẻ từ 1 đến n
    private fun getOddNumbers(n: Int): List<Int> {
        val oddNumbers = mutableListOf<Int>()
        for (i in 1..n step 2) {
            oddNumbers.add(i)
        }
        return oddNumbers
    }

    // Hàm lấy các số chính phương từ 0 đến n
    private fun getSquareNumbers(n: Int): List<Int> {
        val squareNumbers = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            squareNumbers.add(i * i)
            i++
        }
        return squareNumbers
    }
}

