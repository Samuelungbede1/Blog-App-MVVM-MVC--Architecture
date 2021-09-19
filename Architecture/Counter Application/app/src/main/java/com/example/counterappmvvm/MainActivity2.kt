package com.example.counterappmvvm

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainActivity2 : AppCompatActivity() {
    val count : MutableLiveData<Int> = MutableLiveData(0)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        count.observe(this, Observer {
            findViewById<TextView>(R.id.tv_count2).text = it.toString()
        })
        findViewById<Button>(R.id.btn_addnum2).setOnClickListener{
            Addnum()
        }

    }
    fun Addnum(){
        count.value = count.value?.plus(1)
    }
}