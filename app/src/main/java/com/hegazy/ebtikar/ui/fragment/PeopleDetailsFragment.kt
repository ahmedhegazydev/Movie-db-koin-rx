package com.hegazy.ebtikar.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hegazy.ebtikar.databinding.FragmentPeopleDetailsBinding

class PersonDetailsFragment : Fragment() {
    private lateinit var viewDataBinding: FragmentPeopleDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewDataBinding = FragmentPeopleDetailsBinding.inflate(inflater, container, false)
        viewDataBinding.peopleDetailsFragment = this
        return viewDataBinding.root
//        return layoutInflater.inflate(R.layout.fragment_people_details, container, false)
    }


}