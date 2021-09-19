package com.example.architecture_mvvm.api

import com.example.architecture_mvvm.model.Comment
import com.example.architecture_mvvm.model.Post
import com.example.architecture_mvvm.model.Users
import retrofit2.Response
import retrofit2.http.*


interface SimpleApi {
    @GET("users")
    suspend fun getUsers(): Response<List<Users>>


    @GET("posts")
    suspend fun getIndividualUserPost(@Query("userId") userId: Int): Response<List<Post>>

    @GET("posts")
    suspend fun searchForPost(@Query("posts") post: String): Response<List<Post>>


    @GET("comments")
    suspend fun getIndividualUserPostComment(@Query("postId") userId: Int): Response<List<Comment>>

    @POST("comments")
    suspend fun addComment(@Body comment: Comment): Response<Comment>

    @POST("posts")
    suspend fun addPost(@Body post: Post): Response<Post>

}