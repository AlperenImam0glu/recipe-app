package com.example.foodapp.activities

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.foodapp.R
import com.example.foodapp.databinding.ActivityMealBinding
import com.example.foodapp.fragments.HomeFragment
import com.example.foodapp.viewModel.MealViewModel

class MealActivity : AppCompatActivity() {
    private lateinit var mealId: String
    private lateinit var mealName: String
    private lateinit var mealThumb: String
    private lateinit var binding: ActivityMealBinding
    private lateinit var viewModel: MealViewModel
    private lateinit var youtubeLink: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMealBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this).get(MealViewModel::class.java)
        loadingCase()
        getMealInformationFromIntent()
        setInformationInViews()
        onYoutubeImageClick()
        viewModel.getMealDetail(mealId)
        observerMealDetailsLiveData()
    }

    private fun observerMealDetailsLiveData() {
        viewModel.observerMealDetailLivedata().observe(this){
            if(it != null){
                val meal = it
                binding.tvCategory.text = meal.strCategory
                binding.tvArea.text = meal.strArea
                binding.tvInstructions.text = meal.strInstructions
                youtubeLink = meal.strYoutube
                onResponseCase()
            }
        }
    }

    private fun setInformationInViews() {
        Glide.with(this).load(mealThumb).centerCrop().into(binding.imgMealDetail)
        binding.collapsingToolbar.title = mealName
        binding.collapsingToolbar.setCollapsedTitleTextColor(resources.getColor(R.color.white))
        binding.collapsingToolbar.setExpandedTitleColor(resources.getColor(R.color.white))

    }

    private fun onYoutubeImageClick() {
       binding.imgYoutube.setOnClickListener {
           val intent = Intent(Intent.ACTION_VIEW, Uri.parse(youtubeLink))
           startActivity(intent)
       }
    }

    private fun getMealInformationFromIntent() {
        val intent = intent
        mealId = intent.getStringExtra(HomeFragment.MEAL_ID)!!.toString()
        mealName = intent.getStringExtra(HomeFragment.MEAL_NAME)!!.toString()
        mealThumb = intent.getStringExtra(HomeFragment.MEAL_THUMB)!!.toString()
    }

    private fun  loadingCase(){
        binding.progressBar.visibility= View.VISIBLE
        binding.btnAddToFav.visibility= View.INVISIBLE
        binding.tvArea.visibility= View.INVISIBLE
        binding.tvInstructions.visibility= View.INVISIBLE
        binding.tvCategory.visibility= View.INVISIBLE
        binding.tvInstructionsTitle.visibility= View.INVISIBLE
        binding.imgYoutube.visibility= View.INVISIBLE


    }
    private fun  onResponseCase(){
        binding.progressBar.visibility= View.GONE
        binding.btnAddToFav.visibility= View.VISIBLE
        binding.tvArea.visibility= View.VISIBLE
        binding.tvInstructions.visibility= View.VISIBLE
        binding.tvCategory.visibility= View.VISIBLE
        binding.tvInstructionsTitle.visibility= View.VISIBLE
        binding.imgYoutube.visibility= View.VISIBLE
    }
}