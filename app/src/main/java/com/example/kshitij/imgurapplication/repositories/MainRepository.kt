package com.example.kshitij.imgurapplication.repositories

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.example.kshitij.imgurapplication.api.ApiForImgur
import com.example.kshitij.imgurapplication.data.response.Image


import com.example.kshitij.imgurapplication.data.response.ImageData
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val clientIdAuth = "YOUR CLIENT ID"
const val BASE_URL = "https://api.imgur.com/3/gallery/"
class MainRepository(val application: Application) {



    val showProgress = MutableLiveData<Boolean>()
    val imageList = MutableLiveData<List<Image>>()

    fun searchImages(searchString: String){
        showProgress.value = true
        //Network call
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(ApiForImgur::class.java)
        service.getImages(clientIdAuth, searchString).enqueue(object : Callback<ImageData>{
            override fun onFailure(call: Call<ImageData>, t: Throwable) {
                showProgress.value = false
                Toast.makeText(application,  "Error while accessing Api", Toast.LENGTH_LONG).show()
            }

            override fun onResponse(call: Call<ImageData>, response: Response<ImageData>) {
                Log.d("MainRepository", "Response: ${Gson().toJson(response.body())}")
                val imageData = response.body()
                val list = mutableListOf<Image>()
                imageData?.data?.forEach {
                    it?.images?.forEach { image->
                        list.add(image)
                    }
                }
                imageList.value = list


                showProgress.value = false
            }

        })
    }


}