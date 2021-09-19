package com.example.architecturemvc.api

import com.example.architecturemvc.model.Comments
import com.example.architecturemvc.model.Posts
import com.example.architecturemvc.model.Users
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("posts")
    fun getPost(): Call<List<Posts>>

    @GET("users")
    fun getUsers(): Call<List<Users>>

    @GET("comments")
    fun getPostComment(@Query("postId")
                       userId: Int): Call<List<Comments>>

}