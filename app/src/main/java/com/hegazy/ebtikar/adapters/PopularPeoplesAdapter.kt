package com.hegazy.ebtikar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.hegazy.ebtikar.R
import com.hegazy.ebtikar.databinding.ItemPopularPeopleBinding
import com.hegazy.ebtikar.model.PeopleResponse


class PopularPeoplesAdapter(
    private val listener: PeopleItemClickListener,
    private val context: Context
//) : PagingDataAdapter<PeopleResponse.Result, RecyclerView.ViewHolder>(REPO_COMPARATOR) {
//) : PagingDataAdapter<PeopleResponse, RecyclerView.ViewHolder>(REPO_COMPARATOR) {
) : androidx.recyclerview.widget.RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<PeopleResponse.Result> = mutableListOf()

//    override
//    fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PeoplesViewHolder {
//        val layout = R.layout.item_popular_people
//        val v = LayoutInflater
//            .from(parent.context)
//            .inflate(layout, parent, false)
//        return PeoplesViewHolder(v)
//    }

//    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
//        val item = items[position]
//        viewHolder.setPeopleItem(item)
//        viewHolder.itemView.setOnClickListener {
//            listener.onPeopleClick(
//                position,
//                item,
//                viewHolder
//            )
//        }
//    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        PeoplesViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.item_popular_people,
                parent,
                false
            )

        )

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PeoplesViewHolder).itemPeopleBinding.item = items[position]
        val animation = AnimationUtils.loadAnimation(
            context,
            R.anim.anim_bottom
        )
//        holder.itemView.startAnimation(animation)
    }


    override fun getItemCount(): Int {
        return items.size
    }


//    inner class ViewHolder(itemView: View) :
//        androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
//        var imageViewProfile: CircleImageView = itemView.findViewById(R.id.profile_image)
//        var textViewName: TextView = itemView.findViewById(R.id.text_view_name)
//
//        @SuppressLint("SetTextI18n")
//        fun setPeopleItem(item: PeopleResponse.Result) {
//
//            Glide.with(context)
//                .load(ApiUrls.BASE_IMAGE_PATH + item.profile_path)
//                .placeholder(R.drawable.will_smith)
//                .into(imageViewProfile)
//            val words = item.name.split(" ".toRegex(), 3).toTypedArray()
//            if (words.isNotEmpty()) {
//                textViewName.text = words[0]
//            }
//
//        }
//
//    }

    inner class PeoplesViewHolder(val itemPeopleBinding: ItemPopularPeopleBinding) :
        RecyclerView.ViewHolder(itemPeopleBinding.root)


    interface PeopleItemClickListener {
        fun onPeopleClick(position: Int, item: PeopleResponse.Result, viewHolder: PeoplesViewHolder)
    }


    fun setItems(itemsList: MutableList<PeopleResponse.Result>) {
        items.addAll(itemsList)
        notifyDataSetChanged()
    }

    companion object {
        private val REPO_COMPARATOR = object : DiffUtil.ItemCallback<PeopleResponse>() {
            override fun areItemsTheSame(
                oldItem: PeopleResponse,
                newItem: PeopleResponse
            ): Boolean =
                oldItem.page == newItem.page
//                true

            override fun areContentsTheSame(
                oldItem: PeopleResponse,
                newItem: PeopleResponse
            ): Boolean =
                oldItem.equals(newItem)
        }
    }
}



