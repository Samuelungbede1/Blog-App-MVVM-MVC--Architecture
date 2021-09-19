package com.example.architecture_mvvm.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.architecture_mvvm.model.MainViewModel
import com.example.architecture_mvvm.model.MainViewModelFactory
import com.example.architecture_mvvm.R
import com.example.architecture_mvvm.model.Post
import com.example.architecture_mvvm.repository.Repository
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates

class PostActivity : AppCompatActivity(), PostAdapter.OnItemClickListener{
    private lateinit var recyclerView: RecyclerView
    private lateinit var postAdapter: PostAdapter
    private lateinit var postTitle: EditText
    private lateinit var postBody: EditText
    private lateinit var addPostButton: Button
    var listOfPost = ArrayList<Post>()
    var tempListOfPost = ArrayList<Post>()
    private lateinit var viewModel: MainViewModel
    private var userId by Delegates.notNull<Int>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post)


        userId = intent.getIntExtra("userId", 0)


        /** Assigning variables*/
        postTitle = findViewById(R.id.et_PostTitle)
        postBody = findViewById(R.id.et_PostBody)
        addPostButton = findViewById(R.id.btn_AddPostButton)
        recyclerView = findViewById(R.id.rv_PostRecyclerView)
        postAdapter = PostAdapter(this, listOfPost,this)
        var layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager


        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)
        viewModel.getIndividualUserPost(userId)
        viewModel.postResponse.observe(this, Observer { response ->
            if (response.isSuccessful) {
                response.body()?.forEach {
                    postAdapter.addittionalListOfPost(response.body() as ArrayList<Post>)
                    recyclerView.adapter = postAdapter
                }
            }
        })



        //Add A button
        addPostButton.setOnClickListener{
            var titleOfPost = postTitle.text.toString()
            var bodyOfPost = postBody.text.toString()
            var addPost = Post(titleOfPost, bodyOfPost, 9)
             viewModel.addPost(addPost)

            viewModel.addPostResponse.observe(this, Observer{ response ->
                if(response.isSuccessful){
                    postAdapter.addPost(addPost)
                    recyclerView.adapter = postAdapter
                    postTitle.text.clear()
                    postBody.text.clear()
                }
            })
        }
    }


    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        Log.d("mainActivity", "onCreateOptionsMenu: gtyfre")
        menuInflater.inflate(R.menu.menu_item,menu)
        val item = menu?.findItem(R.id.search_item)
        val searchView = item?.actionView as SearchView

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextSubmit(query: String?): Boolean {
                TODO("Not yet implemented")
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                listOfPost.clear()
                val searchText = newText!!.lowercase(Locale.getDefault())
                viewModel.searchNewsResponse(searchText)
                viewModel.searchNews.observe(this@PostActivity, Observer { response ->
                    tempListOfPost.addAll(response.body() as ArrayList<Post>)

                    tempListOfPost.forEach{
                        if(it.title.lowercase(Locale.getDefault()).contains(searchText)){
                          //  tempListOfPost.add(it)
                            postAdapter.addPost(it)
                        }
                    }

                })

                return false
            }

        })

        return super.onCreateOptionsMenu(menu)

    }



    override fun OnItemClick(post: Post) {
        val intent= Intent(this, CommentActivity::class.java)
        intent.putExtra("postId", post.id)
        startActivity(intent)
    }
}