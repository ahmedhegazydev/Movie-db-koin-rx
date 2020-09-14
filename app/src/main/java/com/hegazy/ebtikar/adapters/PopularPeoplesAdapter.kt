package com.hegazy.ebtikar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.hegazy.ebtikar.R
import com.hegazy.ebtikar.databinding.ItemPopularPeopleBinding
import com.hegazy.ebtikar.model.PeopleResponse


class PopularPeoplesAdapter(
    private val listener: PeopleItemClickListener,
    private val context: Context
) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var items: MutableList<PeopleResponse.Result> = mutableListOf()

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
        holder.itemPeopleBinding.root.setOnClickListener {
            listener.onPeopleClick(
                position = position,
                item = items[position],
                viewHolder = holder
            )
        }
    }


    override fun getItemCount(): Int {
        return items.size
    }

    inner class PeoplesViewHolder(val itemPeopleBinding: ItemPopularPeopleBinding) :
        RecyclerView.ViewHolder(itemPeopleBinding.root)

    interface PeopleItemClickListener {
        fun onPeopleClick(position: Int, item: PeopleResponse.Result, viewHolder: PeoplesViewHolder)
    }

    @ExperimentalStdlibApi
    fun setItems(itemsList: MutableList<PeopleResponse.Result>) {
//        itemsList.removeLast()
        items.addAll(itemsList)
        notifyDataSetChanged()
    }

}



