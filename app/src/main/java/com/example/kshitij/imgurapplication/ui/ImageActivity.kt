package com.example.kshitij.imgurapplication.ui


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View.GONE
import android.widget.Toast
import com.example.kshitij.imgurapplication.R
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_image.*
import java.lang.Exception

class ImageActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image)
        val string = intent.getStringExtra("imageUrl")
        //Loading Image
        Picasso.get()
            .load(string)
            .into(imageView,object : Callback{
                override fun onSuccess() {
                    image_view_progress.visibility = GONE
                }
                override fun onError(e: Exception?) {
                    image_view_progress.visibility = GONE
                    Toast.makeText(this@ImageActivity, "Error to load Image", Toast.LENGTH_LONG).show()
                }

            })

    }
}
