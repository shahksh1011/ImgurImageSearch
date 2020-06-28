package com.example.kshitij.imgurapplication.ui

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View.GONE
import android.view.View.VISIBLE

import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.kshitij.imgurapplication.R
import com.example.kshitij.imgurapplication.adapters.ImgurImageAdapter
import com.example.kshitij.imgurapplication.viewmodels.MainViewModel
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity()  {

    private lateinit var viewModel: MainViewModel
    private lateinit var imgurImageAdapter: ImgurImageAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Check Internet Connection
        val cm = baseContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = cm.activeNetworkInfo

        //Initialize ViewModel
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
        viewModel.showProgress.observe(this, Observer {
            if(it)
                progressBar.visibility = VISIBLE
            else
                progressBar.visibility = GONE
        })
        //Initialize Adapter
        imgurImageAdapter = ImgurImageAdapter(this)
        main_activity_recycler_view.adapter = imgurImageAdapter
        viewModel.imageList.observe(this, Observer {
            imgurImageAdapter.setImageList(it)
        })
        //Checking for Internet Connection
        if(networkInfo == null){
            Toast.makeText(this, "Please Connect to Internet", Toast.LENGTH_LONG).show()
            search_button.isClickable = false
            search_edit_text.inputType = InputType.TYPE_NULL
        }else{
            Toast.makeText(this, "Enter Search query in the Search bar", Toast.LENGTH_LONG).show()
            search_button.setOnClickListener {
                if(!search_edit_text.text.isEmpty()){
                    viewModel.searchImages(search_edit_text.text.toString())

                }
            }
        }

    }
}


