package com.hegazy.ebtikar.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hegazy.ebtikar.databinding.FragmentImageOptionsBinding

class ImageOptionsFragment : Fragment() {
    private lateinit var viewDataBinding: FragmentImageOptionsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewDataBinding = FragmentImageOptionsBinding.inflate(inflater, container, false)
        viewDataBinding.imageOptionsFragment = this
        return viewDataBinding.root
//        return layoutInflater.inflate(R.layout.fragment_image_options, container, false)
    }


}