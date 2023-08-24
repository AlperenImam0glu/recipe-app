package com.example.foodapp.viewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.foodapp.models.Meal
import com.example.foodapp.models.MealList
import com.example.foodapp.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MealViewModel: ViewModel() {
    private var mealDetailLiveData = MutableLiveData<Meal>()

    fun getMealDetail(id:String){
        RetrofitInstance.api.getMaelDetail(id).enqueue(object : Callback<MealList> {
            override fun onResponse(call: Call<MealList>, response: Response<MealList>) {
                if (response.body() != null) {
                    mealDetailLiveData.value = response.body()!!.meals[0]
                } else {
                    return
                }
            }
            override fun onFailure(call: Call<MealList>, t: Throwable) {
                Log.e("hata", t.message.toString())
            }

        })
    }

    fun observerMealDetailLivedata(): LiveData<Meal> {
        return mealDetailLiveData
    }
}