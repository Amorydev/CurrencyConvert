package com.amory.currencyconvert.retrofit

import com.amory.currencyconvert.model.CurrencyResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APICallConvert {
    @GET("latest/{currency}")
    fun getRateCurrency(@Path("currency") currency: String): Call<CurrencyResponse>

    @GET("latest/USD")
    fun getCurrencies(): Call<CurrencyResponse>
}