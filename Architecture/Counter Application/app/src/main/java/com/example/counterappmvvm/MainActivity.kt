package com.example.counterappmvvm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.Observer

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var viewModel = MainViewModel()
        viewModel.count.observe(this, Observer {
            findViewById<TextView>(R.id.tv_count).text = it.toString()
        })


        findViewById<Button>(R.id.btn_addnum).setOnClickListener{
            viewModel.Addnum()
        }



        findViewById<Button>(R.id.btn_nextActivity).setOnClickListener{
          val intent = Intent(this, MainActivity2::class.java)
            startActivity(intent)
        }
    }


}