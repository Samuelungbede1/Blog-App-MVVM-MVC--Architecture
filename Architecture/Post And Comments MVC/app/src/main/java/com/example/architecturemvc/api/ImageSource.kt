package com.example.architecturemvc.api

import com.example.architecturemvc.R
import com.example.architecturemvc.model.Posts

object ImageSource {

    private val listOfImages = arrayListOf(
        R.drawable.images_one,
        R.drawable.images_two,
        R.drawable.images_three,
        R.drawable.images_four,
        R.drawable.images_five,
        R.drawable.images_six,
        R.drawable.images_seven,
        R.drawable.images_eight,
        R.drawable.images
    )



    private val listOfUsers = arrayListOf(
        R.drawable.userone,
        R.drawable.user_two,
        R.drawable.user_three,
        R.drawable.user_four,
        R.drawable.user_five,
        R.drawable.user_six,
        R.drawable.user_seven,
    )



    fun getPostImages(): ArrayList<Int>{
        return listOfImages
    }


    fun getUserImage(): ArrayList<Int>{
        return listOfUsers
    }
}