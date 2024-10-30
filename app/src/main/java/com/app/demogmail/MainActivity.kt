package com.app.demogmail

import EmailViewModel
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.demogmail.databinding.ActivityMainBinding
import androidx.activity.viewModels

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var emailAdapter: EmailAdapter
    private val emailViewModel: EmailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflate the layout using ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the Toolbar
        setSupportActionBar(binding.toolbar)

        // Set up the RecyclerView
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        emailAdapter = EmailAdapter(emptyList(), emailViewModel)
        binding.recyclerView.adapter = emailAdapter

        // Handle FloatingActionButton click
        binding.fabCompose.setOnClickListener {
            // Handle compose action
        }
        emailViewModel.emails.observe(this) { emails ->
            emailAdapter.updateEmails(emails)
        }
    }

    private fun sampleEmails(): List<Email> {
        return EmailDataSource().getEmailData()
    }
}
