package com.hegazy.ebtikar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.hegazy.ebtikar.R
import com.hegazy.ebtikar.model.DetailsResponse
import com.hegazy.ebtikar.repo.remote.retrofit.ApiUrls


class PersonImagesAdapter(
    private var mContext: Context? = null,
    private var mData: MutableList<DetailsResponse.Profile>
) : BaseAdapter() {
//    private var mData: MutableList<DetailsResponse.Profile> = mutableListOf()

    fun setData(data: MutableList<DetailsResponse.Profile>) {
        mData = data
        notifyDataSetChanged()
    }

    override fun getCount(): Int {
        return mData.size
    }

    override fun getItem(pos: Int): Any {
        return mData[pos]
    }

    override fun getItemId(pos: Int): Long {
        return pos.toLong()
    }

    override fun getView(
        position: Int,
        convertView: View,
        parent: ViewGroup
    ): View {
        var rowView = convertView
        val item = mData[position]
        if (rowView == null) {
            val inflater =
                mContext!!.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            rowView = inflater.inflate(R.layout.item_people_image, null)
            val viewHolder = ViewHolder()
            viewHolder.image = rowView.findViewById(R.id.image)
            rowView.tag = viewHolder
        }
        val holder =
            rowView.tag as ViewHolder
        Glide.with(holder.image!!.context)
            .load(ApiUrls.BASE_IMAGE_PATH + item.file_path)
            .placeholder(R.drawable.will_smith)
            .into(holder.image!!)
        return rowView
    }

    internal class ViewHolder {
        var image: ImageView? = null
    }
}