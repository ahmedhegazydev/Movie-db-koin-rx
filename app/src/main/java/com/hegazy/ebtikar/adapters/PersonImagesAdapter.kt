package com.hegazy.ebtikar.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.hegazy.ebtikar.R
import com.hegazy.ebtikar.model.DetailsResponse
import com.hegazy.ebtikar.repo.remote.retrofit.ApiUrls
import java.util.*


class PersonImagesAdapter(private var mContext: Context? = null) : BaseAdapter() {
    private var mData =
        ArrayList<DetailsResponse.Profile>(0)

    fun setData(data: ArrayList<DetailsResponse.Profile>) {
        mData = data
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
        var text: TextView? = null
        var image: ImageView? = null
    }
}