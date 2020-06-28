package com.example.kshitij.imgurapplication.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.example.kshitij.imgurapplication.R
import com.example.kshitij.imgurapplication.data.response.Image
import com.example.kshitij.imgurapplication.ui.ImageActivity
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.single_row_image_item.view.*

class ImgurImageAdapter (private val context: Context):
    RecyclerView.Adapter<ImgurImageAdapter.ViewHolder>() {
     var list: List<Image> = ArrayList()
    private val inflater = LayoutInflater.from(context)

    fun setImageList(list: List<Image>){
        this.list = list
        notifyDataSetChanged()
    }
    class ViewHolder(itemView:View): RecyclerView.ViewHolder(itemView){
        val image_solo = itemView.imgur_single_image

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = inflater.inflate(R.layout.single_row_image_item, parent, false)
        return ViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val current = list[position]
        Picasso.get()
            .load(current.link)
            .resize(400,400)
            .centerCrop()
            .into(holder.image_solo)
        holder.image_solo.setOnClickListener {
            val intent = Intent(context, ImageActivity::class.java)
            intent.putExtra("imageUrl", current.link)
            context.startActivity(intent)
        }
    }
}
