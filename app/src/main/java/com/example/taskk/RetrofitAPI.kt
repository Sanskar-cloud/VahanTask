package com.example.vaaahantaskapp

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

interface RetrofitAPI {




    @GET("search")
    fun getUniversities():  retrofit2.Call<List<Universities>>


}

