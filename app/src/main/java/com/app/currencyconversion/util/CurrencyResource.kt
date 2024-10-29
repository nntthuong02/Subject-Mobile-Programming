package com.app.currencyconversion.util

import com.app.currencyconversion.models.CurrencyItem

class CurrencyResource {
    private val currencyData = mutableListOf(
        CurrencyItem(0, "EUR", 27422.1),
        CurrencyItem(1, "USD", 25356.8),
        CurrencyItem(2, "GBP", 32891.6),
        CurrencyItem(3, "INR", 301.5),
        CurrencyItem(4, "CAD", 18254.8),
        CurrencyItem(5, "AUD", 16682.2),
        CurrencyItem(6, "CNY", 3554.9),
        CurrencyItem(7, "JPY", 165.7),
        CurrencyItem(8, "KRW", 18.3),
    )

    fun getAllCurrency(): List<CurrencyItem> {
        return currencyData
    }

    fun getAllTitle(): List<String> {
        return currencyData.map { it.title }
    }
    fun getExchangeRate(title1: String, title2: String): Double? {
        val exchangeRate1 =  currencyData.find { it.title == title1 }?.exchangeRate
        val exchangeRate2 =  currencyData.find { it.title == title2 }?.exchangeRate
        if(exchangeRate2 != null && exchangeRate2 != 0.0){
            return exchangeRate1?.div(exchangeRate2)
        }
        return -1.0
    }


}