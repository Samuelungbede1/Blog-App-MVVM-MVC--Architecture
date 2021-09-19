package com.example.architecture_mvvm.ui

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.architecture_mvvm.model.MainViewModel
import com.example.architecture_mvvm.model.MainViewModelFactory
import com.example.architecture_mvvm.R
import com.example.architecture_mvvm.model.Comment
import com.example.architecture_mvvm.repository.Repository
import kotlin.properties.Delegates

class CommentActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var commentAdapter: CommentAdapter
    private lateinit var commentBody: EditText
    private lateinit var addCommentButton: Button
    var listOfComments = ArrayList<Comment>()
    private lateinit var viewModel: MainViewModel
    private var postId by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)

        postId = intent.getIntExtra("postId", 0)

        /** Assigning variables*/
        recyclerView = findViewById(R.id.rv_CommentRecyclerView)
        commentAdapter = CommentAdapter(this, listOfComments)
        commentBody = findViewById(R.id.et_AddCommentBody)
        addCommentButton = findViewById(R.id.btn_AddCommentButton)


        var layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager

        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getIndividualUserPostComment(postId)

        viewModel.commentResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.forEach {

                //    Log.d(TAG, "onCreate: ")
                    commentAdapter.addittionalListOfComment(response.body() as ArrayList<Comment>)
                    recyclerView.adapter = commentAdapter
                }
            }
        })


        //Add A button
        addCommentButton.setOnClickListener{
            var bodyOfComment = commentBody.text.toString()
            var addComment = Comment(bodyOfComment)
            viewModel.addComment(addComment)

            viewModel.addCommentResponse.observe(this, Observer{ response ->
                if(response.isSuccessful){
                    Log.d(TAG, "${response.code()}")
                    commentAdapter.addComment(addComment)
                    recyclerView.adapter = commentAdapter
                }
            })

        }
    }
}