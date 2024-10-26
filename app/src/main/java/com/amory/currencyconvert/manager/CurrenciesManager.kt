package com.amory.currencyconvert.manager

import com.amory.currencyconvert.model.CurrencyResponse
import com.amory.currencyconvert.retrofit.APICallConvert
import com.amory.currencyconvert.retrofit.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CurrenciesManager {
    private val service = RetrofitClient.retrofitInstance.create(APICallConvert::class.java)

    fun getCurrencies(
        callbackSuccess: (CurrencyResponse) -> Unit,
        callbackFail: (String) -> Unit
    ) {
        val callCurrencies = service.getCurrencies()
        callCurrencies.enqueue(object : Callback<CurrencyResponse> {
            override fun onFailure(call: Call<CurrencyResponse>, t: Throwable) {
                callbackFail(t.message ?: "Unknown error occurred")
            }

            override fun onResponse(
                call: Call<CurrencyResponse>,
                response: Response<CurrencyResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callbackSuccess(it)
                    } ?: callbackFail("Response body is null")
                } else {
                    callbackFail("Failed with code: ${response.code()} - ${response.message()}")
                }
            }
        })
    }

    fun getRateCurrency(code:String,
        callbackSuccess: (CurrencyResponse) -> Unit,
        callbackFail: (String) -> Unit
    ) {
        val callCurrencies = service.getRateCurrency(code)
        callCurrencies.enqueue(object : Callback<CurrencyResponse> {
            override fun onFailure(call: Call<CurrencyResponse>, t: Throwable) {
                callbackFail(t.message ?: "Unknown error occurred")
            }

            override fun onResponse(
                call: Call<CurrencyResponse>,
                response: Response<CurrencyResponse>
            ) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        callbackSuccess(it)
                    } ?: callbackFail("Response body is null")
                } else {
                    callbackFail("Failed with code: ${response.code()} - ${response.message()}")
                }
            }
        })
    }


}