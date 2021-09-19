package com.example.architecture_mvvm.repository

import com.example.architecture_mvvm.api.RetrofitInstance
import com.example.architecture_mvvm.model.Comment
import com.example.architecture_mvvm.model.Post
import com.example.architecture_mvvm.model.Users
import retrofit2.Response

class Repository {

    suspend fun getUsers(): Response<List<Users>>{
        return RetrofitInstance.api.getUsers()
    }


    suspend fun getIndividualUserPost(number: Int): Response<List<Post>>{
        return RetrofitInstance.api.getIndividualUserPost(number)
    }

    suspend fun getIndividualUserPostComment(number: Int) : Response<List<Comment>>{
        return RetrofitInstance.api.getIndividualUserPostComment(number)
    }


    suspend fun addComment (comment: Comment) : Response<Comment>{
        return RetrofitInstance.api.addComment(comment)
    }




    suspend fun addPost (post: Post) : Response<Post>{
        return RetrofitInstance.api.addPost(post)
    }

    suspend fun searchPost(searchTerm: String) :Response<List<Post>>{
        return RetrofitInstance.api.searchForPost(searchTerm)
    }


}