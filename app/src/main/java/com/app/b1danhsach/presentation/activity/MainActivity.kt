package com.app.b1danhsach.presentation.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.b1danhsach.R
import com.app.b1danhsach.presentation.adapter.StudentAdapter
import com.app.b1danhsach.util.StudentDataSource
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var searchEditText: EditText
    private lateinit var studentAdapter: StudentAdapter
    private lateinit var studentDataSource: StudentDataSource

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        recyclerView = findViewById(R.id.recyclerView)
        searchEditText = findViewById(R.id.etSearch)

        // Initialize data source and adapter
        studentDataSource = StudentDataSource()
        studentAdapter = StudentAdapter(studentDataSource.getStudentData())

        // Setup RecyclerView
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = studentAdapter

        // Setup search functionality
        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                filter(s.toString())
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        })
    }

    // Filter function
    private fun filter(text: String) {
        val filteredList = if (text.length > 2) {
            studentDataSource.getStudentData().filter {
                it.mssv.contains(text, ignoreCase = true) ||
                        it.name.contains(text, ignoreCase = true)
            }
        } else {
            studentDataSource.getStudentData() // Show full list if text length <= 2
        }
        studentAdapter.updateList(filteredList)
    }
}

