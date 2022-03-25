package com.example.proga

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.proga.data.firebase.UserFirebase
import de.hdodenhof.circleimageview.CircleImageView


class CustomRecyclerAdapter(private val userFirebase: List<UserFirebase>) :
    RecyclerView.Adapter<CustomRecyclerAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var userName: TextView = itemView.findViewById(R.id.user_name)
        var userRecommendation: TextView = itemView.findViewById(R.id.recomendation)
        var userImage: CircleImageView = itemView.findViewById(R.id.recommendation_image)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_rc_view_people, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.userName.text = userFirebase[position].name
        holder.userRecommendation.text = userFirebase[position].name2
        Glide.with(holder.userImage.context).load("https://mobimg.b-cdn.net/v3/fetch/e4/e4827f228c6f874e6b089a43254cd61a.jpeg").into(holder.userImage)
    }

    override fun getItemCount(): Int {
        return  userFirebase.size
    }
}



