package com.hegazy.ebtikar.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.hegazy.ebtikar.R
import com.hegazy.ebtikar.model.DetailsResponse
import com.hegazy.ebtikar.repo.remote.retrofit.ApiUrls
import de.hdodenhof.circleimageview.CircleImageView


class PeopleImagesAdapter(
    private val listener: PeopleImagesAdapter.ImageItemClickListener,
    private val context: Context
) : androidx.recyclerview.widget.RecyclerView.Adapter<PeopleImagesAdapter.ViewHolder>() {

    private var items: MutableList<DetailsResponse.Profile> = mutableListOf()

    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = R.layout.item_people_image
        val v = LayoutInflater
            .from(parent.context)
            .inflate(layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = items[position]

        viewHolder.setImageItem(item)
        viewHolder.itemView.setOnClickListener {
            listener.onImageClick(
                position,
                item,
                viewHolder
            )
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class ViewHolder(itemView: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        var imageViewProfile: CircleImageView = itemView.findViewById(R.id.image)

        @SuppressLint("SetTextI18n")
        fun setImageItem(item: DetailsResponse.Profile) {

            Glide
                .with(context)
                .load(ApiUrls.BASE_IMAGE_PATH + item.file_path)
                .placeholder(R.drawable.will_smith)
                .into(imageViewProfile)
        }

    }


    interface ImageItemClickListener {
        fun onImageClick(position: Int, item: DetailsResponse.Profile, viewHolder: ViewHolder)
    }

    fun setItems(itemsList: MutableList<DetailsResponse.Profile>) {
        items = itemsList
        notifyDataSetChanged()

    }
}



