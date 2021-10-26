package com.grace.bookswap

import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.SurfaceHolder
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView
import org.w3c.dom.NameList
import java.util.ArrayList

class CustomerAdapter(private val bookList: ArrayList<item>) :
    RecyclerView.Adapter<CustomerAdapter.MyViewMolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewMolder {
       val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item,parent, false)
        return MyViewMolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewMolder, position: Int) {
        val currentItem = bookList[position]
        holder.titleImage.setImageResource(currentItem.titleImage)
        holder.tvHeading.text = currentItem.heading
    }

    override fun getItemCount(): Int {
        return bookList.size
    }

    class MyViewMolder(itemView: View) : RecyclerView.ViewHolder(itemView){

        val titleImage : ShapeableImageView = itemView.findViewById(R.id.profilePic)
        val tvHeading : TextView = itemView.findViewById(R.id.capturedLine)
    }
}