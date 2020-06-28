package com.example.kshitij.imgurapplication.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.kshitij.imgurapplication.data.response.Image
import com.example.kshitij.imgurapplication.repositories.MainRepository

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = MainRepository(application)
    val imageList: LiveData<List<Image>>
    val showProgress :LiveData<Boolean>
    init {
        this.showProgress = repository.showProgress
        this.imageList = repository.imageList
    }

    fun searchImages(searchString: String){
        repository.searchImages(searchString)
    }
}