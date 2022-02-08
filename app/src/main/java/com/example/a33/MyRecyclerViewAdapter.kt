package com.example.a33

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.imageview.ShapeableImageView

class MyRecyclerViewAdapter(private val personsList : ArrayList<Person>) : RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentItem = personsList[position]
        holder.titleImage.setImageResource(currentItem.sex)
        holder.personName.setText(currentItem.name)
        holder.personPhone.setText(currentItem.phone)

    }

    override fun getItemCount(): Int {
        return personsList.size
    }


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleImage : ShapeableImageView = itemView.findViewById(R.id.title_image)
        val personName : TextView = itemView.findViewById(R.id.name)
        val personPhone: TextView = itemView.findViewById(R.id.phone)


    }
}
