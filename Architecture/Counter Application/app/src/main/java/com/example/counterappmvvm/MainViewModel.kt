package com.example.counterappmvvm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class MainViewModel: ViewModel() {
    val count : MutableLiveData<Int> = MutableLiveData(0)



    fun Addnum(){
        viewModelScope.launch {
            count.value = count.value?.plus(1)
        }
    }
}