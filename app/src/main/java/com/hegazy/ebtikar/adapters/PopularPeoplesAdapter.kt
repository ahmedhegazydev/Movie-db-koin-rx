package com.hegazy.ebtikar.adapters

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.hegazy.ebtikar.R
import com.hegazy.ebtikar.model.PeopleItem
import de.hdodenhof.circleimageview.CircleImageView


class PopularPeoplesAdapter(
    private val listener: PeopleItemClickListener,
    private val context: Context
) : androidx.recyclerview.widget.RecyclerView.Adapter<PopularPeoplesAdapter.ViewHolder>() {

    var isLoading = false
    private var items: MutableList<ClipData.Item> = mutableListOf()


    fun setItems(itemsList: MutableList<ClipData.Item>) {
        items = itemsList
    }


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

        viewHolder.setGroceryItem(item)

    }

    override fun getItemCount(): Int {
        return items.size
    }


    inner class ViewHolder(itemView: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        var imageViewProfile: CircleImageView = itemView.findViewById(R.id.profile_image)
        var textViewName: TextView = itemView.findViewById(R.id.text_view_name)

        @SuppressLint("SetTextI18n")
        fun setGroceryItem(item: ClipData.Item) {

//            clIncrementOnly.visibility = View.VISIBLE
//            tvName.text = item.name
//            tvDetails.text = item.unitOfMeasure


        }

    }


    interface PeopleItemClickListener {
        fun onPeopleClick(position: Int, item: PeopleItem, viewHolder: ViewHolder)
    }

}



