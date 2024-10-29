package com.app.currencyconversion

import android.R
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import com.app.currencyconversion.databinding.ActivityMainBinding
import com.app.currencyconversion.util.CurrencyResource

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupSpinners()
        binding.etSourceAmount.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                convertCurrency()
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })
        binding.spinnerSourceCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                convertCurrency()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }

        binding.spinnerTargetCurrency.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                convertCurrency()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
    }

    private fun setupSpinners() {
        val currencies = CurrencyResource().getAllTitle()
        val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, currencies)
        adapter.setDropDownViewResource(R.layout.simple_spinner_dropdown_item)

        binding.spinnerSourceCurrency.adapter = adapter
        binding.spinnerTargetCurrency.adapter = adapter
    }
    private fun convertCurrency() {
        val amountText = binding.etSourceAmount.text.toString()
        if (amountText.isNotEmpty()) {
            val sourceCurrency = binding.spinnerSourceCurrency.selectedItem.toString()
            val targetCurrency = binding.spinnerTargetCurrency.selectedItem.toString()
            val sourceAmount = amountText.toDouble()

            val exchangeRate = CurrencyResource().getExchangeRate(sourceCurrency, targetCurrency)
            val targetAmount = sourceAmount * exchangeRate!!

            binding.etTargetAmount.setText(targetAmount.toString())
        }
    }
}