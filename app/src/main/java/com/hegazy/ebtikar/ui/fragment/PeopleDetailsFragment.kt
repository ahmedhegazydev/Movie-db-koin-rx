package com.hegazy.ebtikar.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hegazy.ebtikar.databinding.FragmentPeopleDetailsBinding
import timber.log.Timber

class PersonDetailsFragment : Fragment() {
    private lateinit var viewDataBinding: FragmentPeopleDetailsBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentPeopleDetailsBinding.inflate(inflater, container, false)
        viewDataBinding.peopleDetailsFragment = this
        return viewDataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Timber.d("onViewCreated PersonDetailsFragment")


    }


}