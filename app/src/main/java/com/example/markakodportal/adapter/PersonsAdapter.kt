package com.example.markakodportal.adapter

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CircleCrop
import com.bumptech.glide.request.RequestOptions
import com.example.markakodportal.Profile
import com.example.markakodportal.R

class PersonsAdapter(private val profileList: List<Profile>) : RecyclerView.Adapter<PersonsAdapter.ProfileViewHolder>() {

    // Profile öğelerini temsil eden iç sınıf
    class ProfileViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val profileImage: ImageView = itemView.findViewById(R.id.imgPerson)
        val txtPersonName: TextView = itemView.findViewById(R.id.txtPersonName)
        val emailTextView: TextView = itemView.findViewById(R.id.txtEmail)
        val positionTextView: TextView = itemView.findViewById(R.id.txtPosition)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProfileViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_persons, parent, false)
        return ProfileViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProfileViewHolder, position: Int) {
        val profile = profileList[position]
        val profileImageUrl = profile.profileImageUrl

        // Glide kütüphanesi ile resmi ImageView'e yükleyin
        Glide.with(holder.itemView.context)
            .load(profileImageUrl)
            .apply(RequestOptions.bitmapTransform(CircleCrop()))
            .into(holder.profileImage)


        holder.emailTextView.text = profile.email
        holder.positionTextView.text = profile.position
        holder.txtPersonName.text = profile.name
    }

    override fun getItemCount(): Int {
        return profileList.size
    }
}