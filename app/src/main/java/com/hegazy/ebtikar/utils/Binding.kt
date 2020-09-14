package com.hegazy.ebtikar.utils

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.hegazy.ebtikar.adapters.PopularPeoplesAdapter
import com.hegazy.ebtikar.model.PeopleResponse
import com.hegazy.ebtikar.repo.remote.retrofit.ApiUrls
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch


@BindingAdapter("app:pagingItems")
//fun setPagingItems(listView: RecyclerView, items: PagingData<PeopleResponse.Result>?) {
//fun setPagingItems(listView: RecyclerView, items: PagingData<PeopleResponse>?) {
fun setPagingItems(listView: RecyclerView, items: MutableList<PeopleResponse.Result>?) {
    GlobalScope.launch {
        items?.let {
            listView.visibility = View.VISIBLE
            (listView.adapter as PopularPeoplesAdapter).apply {
//                this.submitData(items)
                this.setItems(items)
            }
        }
    }
}

@BindingAdapter("app:image_path_to_load")
fun loadImage(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        Glide.with(view).load(ApiUrls.BASE_IMAGE_PATH + url).into(view)
    }
}