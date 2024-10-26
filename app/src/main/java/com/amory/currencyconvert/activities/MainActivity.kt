package com.amory.currencyconvert.activities

import android.R
import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.amory.currencyconvert.databinding.ActivityMainBinding
import com.amory.currencyconvert.utils.Utils
import com.amory.currencyconvert.viewModel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()
    private var listCurrency: Map<String, Double> = emptyMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Utils.kiemTraKetNoi(this)
        setUpObserver()
        setUpClickListener()
    }

    private fun setUpClickListener() {
        binding.btnConvert.setOnClickListener {
            val fromCurrencyCode = binding.spnFrom.selectedItem.toString()
            val toCurrencyCode = binding.spnTo.selectedItem.toString()
            viewModel.fetchDataRateCurrency(fromCurrencyCode)
            convertToAmount(toCurrencyCode)
        }
        binding.imbSwap.setOnClickListener {
            val fromCurrency = binding.spnFrom.selectedItemPosition
            val toCurrency = binding.spnTo.selectedItemPosition
            binding.spnFrom.setSelection(toCurrency)
            binding.spnTo.setSelection(fromCurrency)
        }
    }

    private fun setUpObserver() {
        viewModel.fetchDataCurrencies()

        viewModel.listCurrencies.observe(this) { listCurrencies ->
            val adapter = ArrayAdapter(this, R.layout.simple_spinner_item, listCurrencies)
            binding.spnFrom.adapter = adapter
            binding.spnTo.adapter = adapter
        }

        viewModel.list.observe(this) { list ->
            listCurrency = list
            Log.d("MainActivity", "Updated listCurrency: $listCurrency")
        }
    }

    @SuppressLint("DefaultLocale")
    private fun convertToAmount(toCurrencyCode:String) {

        if (binding.edtAmount.text.isNullOrEmpty()) {
            binding.amountConvert.text = "Please enter an amount"
            return
        }

        val amount = binding.edtAmount.text.toString().toDoubleOrNull()
        if (amount == null) {
            binding.amountConvert.text = "Invalid amount"
            return
        }

        if (listCurrency.containsKey(toCurrencyCode)) {
            val rate = listCurrency[toCurrencyCode] ?: 1.0
            val totalAmount = rate * amount
            binding.amountConvert.text = String.format("%.2f", totalAmount)
        } else {
            binding.amountConvert.text = "Currency not available"
        }
    }
}
