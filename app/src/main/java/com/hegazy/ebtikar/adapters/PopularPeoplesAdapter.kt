package com.hegazy.ebtikar.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hegazy.ebtikar.R
import com.hegazy.ebtikar.model.PeopleItem
import com.hegazy.ebtikar.model.PeopleResponse
import com.hegazy.ebtikar.repo.remote.retrofit.NetworkConstants
import de.hdodenhof.circleimageview.CircleImageView


class PopularPeoplesAdapter(
    private val listener: PeopleItemClickListener,
    private val context: Context
) : androidx.recyclerview.widget.RecyclerView.Adapter<PopularPeoplesAdapter.ViewHolder>() {

    var isLoading = false
    private var items: MutableList<PeopleResponse.Result> = mutableListOf()


    override
    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layout = R.layout.item_popular_people
        val v = LayoutInflater
            .from(parent.context)
            .inflate(layout, parent, false)
        return ViewHolder(v)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val item = items[position]

        viewHolder.setPeopleItem(item)

    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class ViewHolder(itemView: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        var imageViewProfile: CircleImageView = itemView.findViewById(R.id.profile_image)
        var textViewName: TextView = itemView.findViewById(R.id.text_view_name)

        @SuppressLint("SetTextI18n")
        fun setPeopleItem(item: PeopleResponse.Result) {

            Glide
                .with(context)
                .load(NetworkConstants.BASE_IMAGE_PATH + item.profile_path)
                .placeholder(R.drawable.will_smith)
                .into(imageViewProfile)
            textViewName.text = item.name

        }

    }


    interface PeopleItemClickListener {
        fun onPeopleClick(position: Int, item: PeopleItem, viewHolder: ViewHolder)
    }


    fun setItems(itemsList: MutableList<PeopleResponse.Result>) {
        items = itemsList
        notifyDataSetChanged()

    }
}



