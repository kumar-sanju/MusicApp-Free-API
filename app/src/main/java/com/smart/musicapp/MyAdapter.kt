package com.smart.musicapp

import android.app.Activity
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter(val context: Activity, val dataList: List<Data>):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.each_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val currentData = dataList[position]

        val musicPlayer = MediaPlayer.create(context, currentData.preview.toUri())
        holder.title.text = currentData.title
        Picasso.get().load(currentData.album.md5_image).into(holder.image)

        holder.play.setOnClickListener{
            musicPlayer.start()
        }
        holder.pause.setOnClickListener{
            musicPlayer.pause()
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val image : ImageView
        val title: TextView
        val play: ImageButton
        val pause: ImageButton

        init {
            image= itemView.findViewById(R.id.musicImage)
            title= itemView.findViewById(R.id.musicTitle)
            play= itemView.findViewById(R.id.playBtn)
            pause= itemView.findViewById(R.id.pauseBtn)
        }
    }

}