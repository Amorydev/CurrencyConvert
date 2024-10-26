package com.amory.currencyconvert.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.amory.currencyconvert.manager.CurrenciesManager.getCurrencies
import com.amory.currencyconvert.manager.CurrenciesManager.getRateCurrency

class MainViewModel : ViewModel() {
    private var _listCurrencies = MutableLiveData<List<String>>()
    val listCurrencies: LiveData<List<String>> get() = _listCurrencies

    private var _list = MutableLiveData<Map<String, Double>>()
    val list: LiveData<Map<String, Double>> get() = _list

    fun fetchDataCurrencies() {
        getCurrencies(
            { currencyResponse ->
                val list = currencyResponse.conversion_rates?.keys?.map { it } ?: emptyList()
                Log.d("list", list.toString())
                _listCurrencies.value = list
            },
            { errorMessage ->
            }
        )
    }

    fun fetchDataRateCurrency(code:String) {
        getRateCurrency(code,
            { currencyResponse ->
                Log.d("list", currencyResponse.toString())
                _list.value = currencyResponse.conversion_rates
            },
            { errorMessage ->
            }
        )
    }
}
