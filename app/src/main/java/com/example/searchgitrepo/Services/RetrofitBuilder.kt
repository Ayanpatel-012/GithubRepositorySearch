package com.example.searchgitrepo.Services

import com.example.searchgitrepo.Utils.ApiConstants.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private val retrofitInstance=Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build()

    fun getApiService():ApiServices{
        return retrofitInstance.create(ApiServices::class.java)
    }

}