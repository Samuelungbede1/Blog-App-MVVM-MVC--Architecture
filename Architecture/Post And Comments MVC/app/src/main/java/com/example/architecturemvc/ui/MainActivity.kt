package com.example.architecturemvc.ui

import android.content.ContentValues.TAG
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturemvc.R
import com.example.architecturemvc.api.ApiService
import com.example.architecturemvc.api.ImageSource
import com.example.architecturemvc.model.Posts
import com.example.architecturemvc.model.Users
import com.example.architecturemvc.utils.Constants.Companion.BASE_URL
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity(), PostAdapter.OnItemClickListener, PostAdapter.OnAddCommentClickListener{
    private lateinit var retrofit: Retrofit
    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter
    //private lateinit var addCommentTextInput: EditText
    // private lateinit var comment: String
    var listOfPost = ArrayList<Posts>()
    var listOfUsers = ArrayList<Users>()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val listOfPostImage = ImageSource.getPostImages()
        val listOfUserImage = ImageSource.getUserImage()

        var addCommentTextInput = findViewById<EditText>(R.id.et_AddCommentText)
       // comment = addCommentTextInput.text.toString()






        /**Assigning variables*/
        recyclerView = findViewById(R.id.rv_RecyclerView)
        postAdapter = PostAdapter(this, listOfPost,listOfUsers
            ,listOfPostImage,listOfUserImage, this, this)
        recyclerView.layoutManager = LinearLayoutManager(this)


        /**Creating and building the retrofit*/
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        obtainData ()

    }


    /**Function that obtains the data from the API as a response*/
    private fun obtainData () {
        retrofit.create(ApiService::class.java)
            .getPost()
            .enqueue(object: Callback<List<Posts>> {
                override fun onResponse (call: Call<List<Posts>>, response: Response<List<Posts>>) {
                    if(response.isSuccessful){
                        var posts: List<Posts> = response.body()!!
                        postAdapter.addPost(posts as ArrayList<Posts>)
                        recyclerView.adapter = postAdapter
                    } else{
                    }
                }

                override fun onFailure(call: Call<List<Posts>>, t: Throwable) {
                  Log.e(TAG, "onFailure: "+ t.message)
                }
            })


        retrofit.create(ApiService::class.java)
            .getUsers()
            .enqueue(object: Callback<List<Users>>{
                override fun onResponse(call: Call<List<Users>>, response: Response<List<Users>>) {
                    if(response.isSuccessful){
                        var users: List<Users> = response.body()!!
                        postAdapter.addUsers(users as ArrayList<Users>)
                        recyclerView.adapter = postAdapter
                    } else{
                    }
                }

                override fun onFailure(call: Call<List<Users>>, t: Throwable) {
                    Log.e(TAG, "onFailure: "+ t.message)
                }

            })
    }


    /**Implement the commentIcon Click*/
    override fun OnItemClick(post: Posts, user: Users) {
        val intent= Intent(this, CommentActivity::class.java)
        intent.putExtra("postId", post.id)
        intent.putExtra("postTitle", post.title)
        intent.putExtra("postBody", post.body)
        intent.putExtra("userImage",postAdapter.userImage[post.image])
        intent.putExtra("userName",user.name)
        startActivity(intent)
    }

    override fun addCommentClick(post: Posts) {
       // AddCommentTextInputIsEmpty(comment)
        val intent= Intent(this, CommentActivity::class.java)
        intent.putExtra("postId", post.id)
        intent.putExtra("postTitle", post.title)
        intent.putExtra("postBody", post.body)
        intent.putExtra("userImage",postAdapter.userImage[post.image])
        startActivity(intent)
    }



}