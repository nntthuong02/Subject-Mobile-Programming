package com.app.demogmail

import EmailViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.demogmail.databinding.ActivityMainBinding
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var emailAdapter: EmailAdapter
    private val emailViewModel: EmailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        emailAdapter = EmailAdapter(emptyList(), emailViewModel)
        binding.recyclerView.adapter = emailAdapter

        // Handle FloatingActionButton click
        binding.fabCompose.setOnClickListener {
            val selectedCount = emailViewModel.getSelectedEmailCount() // Phương thức trong ViewModel
            val favoriteCount = emailViewModel.getFavoriteEmailCount()
            Toast.makeText(this, "Tổng số email được chọn: $selectedCount \n Tổng số email ưa thích: $favoriteCount", Toast.LENGTH_SHORT).show()
        }
        emailViewModel.emails.observe(this) { emails ->
            emailAdapter.updateEmails(emails)
        }
    }

    private fun sampleEmails(): List<Email> {
        return EmailDataSource().getEmailData()
    }
}
