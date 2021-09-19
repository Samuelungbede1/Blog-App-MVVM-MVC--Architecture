package com.example.architecture_mvvm.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.architecture_mvvm.model.MainViewModel
import com.example.architecture_mvvm.model.MainViewModelFactory
import com.example.architecture_mvvm.R
import com.example.architecture_mvvm.model.Users
import com.example.architecture_mvvm.repository.Repository

class MainActivity : AppCompatActivity(), UserAdapter.OnItemClickListener {

    private lateinit var recyclerView: RecyclerView
    private lateinit var userAdapter: UserAdapter
    var listOfUsers =  ArrayList<Users>()
    private lateinit var viewModel: MainViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        /** Assigning variables*/
        recyclerView = findViewById(R.id.rv_UserIDRecyclerView)
        userAdapter = UserAdapter(this, listOfUsers, this)
        var layoutManager = LinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager




        val repository = Repository()
        val viewModelFactory = MainViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MainViewModel::class.java)


        viewModel.getUsers()
        viewModel.userResponse.observe(this, Observer {response->
            if(response.isSuccessful){
                response.body()?.forEach {

                    userAdapter.addittionalListOfUsers(response.body() as ArrayList<Users>)
                    recyclerView.adapter = userAdapter
                }
            }
        })
    }

    override fun OnItemClick(user: Users) {
        val intent= Intent(this, PostActivity::class.java)
        intent.putExtra("userId", user.id)
        startActivity(intent)
    }
}