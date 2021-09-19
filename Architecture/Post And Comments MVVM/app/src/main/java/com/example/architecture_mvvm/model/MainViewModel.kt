package com.example.architecture_mvvm.model

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.architecture_mvvm.model.Comment
import com.example.architecture_mvvm.model.Post
import com.example.architecture_mvvm.model.Users
import com.example.architecture_mvvm.repository.Repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel (private val repository: Repository): ViewModel() {
    val userResponse : MutableLiveData<Response<List<Users>>> = MutableLiveData()
    val postResponse : MutableLiveData<Response<List<Post>>> = MutableLiveData()
    val commentResponse : MutableLiveData<Response<List<Comment>>> = MutableLiveData()
    val addCommentResponse : MutableLiveData<Response<Comment>> = MutableLiveData()
    val addPostResponse : MutableLiveData<Response<Post>> = MutableLiveData()
    val searchNews : MutableLiveData<Response<List<Post>>> = MutableLiveData()


    fun getUsers(){
        viewModelScope.launch {
            val response = repository.getUsers()
            userResponse.value = response
        }
    }

   fun getIndividualUserPost(number: Int){
       viewModelScope.launch {
           val response = repository.getIndividualUserPost(number)
           postResponse.value = response
       }
   }


    fun getIndividualUserPostComment(number: Int){
        viewModelScope.launch {
            val response = repository.getIndividualUserPostComment(number)
            commentResponse.value = response
        }
    }


    fun addComment(comment: Comment){
        viewModelScope.launch {
            val response = repository.addComment(comment)
            addCommentResponse.value = response
        }
    }


    fun addPost(post: Post){
        viewModelScope.launch {
            val response = repository.addPost(post)
            addPostResponse.value = response
        }
    }


    fun searchNewsResponse(searchTerm: String){
        viewModelScope.launch {
            val response = repository.searchPost(searchTerm)
            searchNews.value = response
        }
    }
}