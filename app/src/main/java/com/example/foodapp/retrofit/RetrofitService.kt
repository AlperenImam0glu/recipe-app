package com.example.foodapp.retrofit

import com.example.foodapp.models.MealList
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {


    @GET("random.php")
    fun getRandomMeal(): Call<MealList>

    @GET("lookup.php?")
    fun getMaelDetail(@Query("i") id:String): Call<MealList>
}