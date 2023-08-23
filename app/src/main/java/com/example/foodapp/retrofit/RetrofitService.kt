package com.example.foodapp.retrofit

import com.example.foodapp.models.MealList
import retrofit2.Call
import retrofit2.http.GET

interface RetrofitService {


    @GET("random.php")
    fun getRandomMeal(): Call<MealList>
}