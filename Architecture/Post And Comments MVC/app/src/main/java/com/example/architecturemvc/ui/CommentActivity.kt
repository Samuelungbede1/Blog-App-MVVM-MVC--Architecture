package com.example.architecturemvc.ui

import android.content.ContentValues
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.architecturemvc.R
import com.example.architecturemvc.api.ApiService
import com.example.architecturemvc.api.ImageSource
import com.example.architecturemvc.model.Comments
import com.example.architecturemvc.utils.Constants
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.properties.Delegates

class CommentActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var commentAdapter: CommentAdapter
    private var listOfComments = ArrayList<Comments>()
    private lateinit var retrofit: Retrofit
    private var postId by Delegates.notNull<Int>()
    private lateinit var postTitle : String
    private lateinit var postBody : String
    private lateinit var userName : String
    private lateinit var textViewPostTitle : TextView
    private lateinit var textViewPostBody : TextView
    private lateinit var textViewUserName : TextView
    private lateinit var userImage :ImageView
    private lateinit var commentImage :ImageView
    private lateinit var addCommentEditText: EditText
   // private lateinit var comment: String


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        val listOfUserImage = ImageSource.getUserImage()
        textViewPostTitle = findViewById(R.id.tv_CommentActivityPostTitle)
        textViewPostBody = findViewById(R.id.tv_postBodyCommentActivity)
        userImage = findViewById(R.id.iv_userImageCommentActivity)
        commentImage = findViewById(R.id.iv_commenterImageCommentActivity)
        textViewUserName = findViewById(R.id.tv_userNameCommentActivity)
        addCommentEditText = findViewById(R.id.et_CommentActivity)
        var postText : TextView = findViewById(R.id.tv_PostActivity)



        /** Assigning variables*/
        recyclerView = findViewById(R.id.rv_recyclerViewCommentActivity)
        commentAdapter = CommentAdapter(listOfComments,listOfUserImage)
        postId = intent.getIntExtra("postId", 0)
        postTitle = intent.getStringExtra("postTitle").toString()
        textViewPostTitle.text = postTitle
        postBody = intent.getStringExtra("postBody").toString()
        userName = intent.getStringExtra("userName").toString()
        textViewUserName.text = userName
        textViewPostBody.text = postBody
        var image = intent.getIntExtra("userImage",0)
        var imageOfComment = intent.getIntExtra("userImage",0)
        userImage.setImageResource(image)
        commentImage.setImageResource(imageOfComment)
        recyclerView.layoutManager = LinearLayoutManager(this)


        /**Creating and building the retrofit*/
        retrofit = Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        obtainData ()


        postText.setOnClickListener {
            var comment = addCommentEditText.text.toString()
            var newComment = Comments("email",comment)
            commentAdapter.addComment(newComment)
         //if( AddCommentTextInputIsEmpty(comment))
        }



    }


    /**Function that obtains the data from the API as a response*/
    private fun obtainData () {
        retrofit.create(ApiService::class.java)
            .getPostComment(postId)
            .enqueue(object: Callback<List<Comments>> {
                override fun onResponse (call: Call<List<Comments>>, response: Response<List<Comments>>) {
                    if(response.isSuccessful) {
                        val comments: List<Comments> = response.body()!!
                      Log.d("Response", "$comments")
                          commentAdapter.addComments(comments as ArrayList<Comments>)
                          recyclerView.adapter = commentAdapter
                    } else{
                    }
                }

                override fun onFailure(call: Call<List<Comments>>, t: Throwable) {
                    Log.e(ContentValues.TAG, "onFailure: "+ t.message)
                }
            })
    }


    fun AddCommentTextInputIsEmpty(text: String): Boolean{
        if(text.isEmpty()){
            Toast.makeText(this, "Please Write Something", Toast.LENGTH_SHORT).show()
        }
        return true
    }
}