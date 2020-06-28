package com.example.kshitij.imgurapplication.api

import com.example.kshitij.imgurapplication.data.response.ImageData
import retrofit2.Call

import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
interface ApiForImgur{

    @GET("search/")
    fun getImages(
        @Header("Authorization") clientId: String,
        @Query("q")searchString: String
    ): Call<ImageData>

}
